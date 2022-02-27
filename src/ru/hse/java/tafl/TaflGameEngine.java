package ru.hse.java.tafl;

import ru.hse.java.engine.AbstractGameEngine;
import ru.hse.java.engine.DeskFactory;
import ru.hse.java.exception.InvalidMoveException;
import ru.hse.java.model.*;

import ru.hse.java.tafl.model.TaflFigureType;
import ru.hse.java.tafl.model.TaflMove;

import java.util.List;

public class TaflGameEngine extends AbstractGameEngine<TaflMove, TaflDesk> {
    public TaflGameEngine(Player firstPlayer, DeskFactory<TaflDesk> deskFactory) {
        super(firstPlayer, deskFactory);
    }

    @Override
    protected void doMove(TaflMove move) throws InvalidMoveException {
        validateMove(move);
        Figure figure = getFigure(move.from());
        desk.removeFigure(move.from());
        desk.setFigure(move.to(), figure);

        for (Position step : Position.getSteps()) {
            Position enemyCandidate = move.to().add(step);
            if (isWarriorCaptured(enemyCandidate, step)) {
                desk.removeFigure(enemyCandidate);
            }
            if (isKingCaptured(enemyCandidate)) {
                setStatus(Status.WIN);
                return;
            }
        }

        if (figure.getType() == TaflFigureType.KING && desk.isCornerCell(move.to())) {
            setStatus(Status.WIN);
        }
        if (enemyCantMove(getCurrentPlayer())) {
            setStatus(Status.WIN);
        }
    }

    private boolean enemyCantMove(Player currentPlayer) {
        List<List<Position>> lines = desk.asLines();
        for (List<Position> line : lines) {
            for (Position position : line) {
                final Cell cell = desk.cellAt(position);

                if (!cell.hasFigure() || cell.getFigure().ownedBy() == currentPlayer) {
                    continue;
                }

                final Figure figure = cell.getFigure();
                // check that the figure has at least one possible move
                for (final Position direction : Position.getSteps()) {
                    final Position candidate = position.add(direction);

                    if (desk.contains(candidate) &&
                            !desk.cellAt(candidate).hasFigure() &&
                            isCellAvailableForTheFigure(candidate, figure)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isCellAvailableForTheFigure(final Position cell, final Figure figure) {
        return
                figure.getType() == TaflFigureType.WARRIOR ?
                        !desk.isCenterCell(cell) && !desk.isCornerCell(cell) :
                        !desk.isCenterCell(cell) || isKingAllowedToReturnToTheThrone();
    }

    private boolean isFigureMoveDistanceRestricted() {
        return true;
    }

    private boolean isKingAllowedToReturnToTheThrone() {
        return true;
    }

    private boolean isKingCaptured(Position candidate) {
        if (!hasEnemyFigure(candidate, TaflFigureType.KING)) {
            return false;
        }
        Figure king = desk.cellAt(candidate).getFigure();

        for (final Position direction : Position.getSteps()) {
            final Position supporter = candidate.add(direction);

            if (desk.contains(supporter) &&
                    !desk.isCornerCell(supporter) &&
                    !desk.isCenterCell(supporter) &&
                    (!desk.cellAt(supporter).hasFigure() || desk.cellAt(supporter).getFigure().ownedBy() == king.ownedBy())) {
                return false;
            }
        }

        return true;
    }

    private boolean hasEnemyFigure(Position candidate, TaflFigureType type) {
        return desk.contains(candidate) &&
                desk.cellAt(candidate).hasFigure() &&
                desk.cellAt(candidate).getFigure().getType() == type &&
                desk.cellAt(candidate).getFigure().ownedBy() != getCurrentPlayer();
    }

    private boolean isWarriorCaptured(Position candidate, Position step) {
        if (hasEnemyFigure(candidate, TaflFigureType.WARRIOR)) {
            Position helper = candidate.add(step);
            Figure warrior = desk.cellAt(candidate).getFigure();
            return desk.contains(helper) &&
                    (desk.isCornerCell(helper) ||
                            desk.cellAt(helper).hasFigure() && desk.cellAt(helper).getFigure().ownedBy() != warrior.ownedBy());
        }
        return false;
    }

    private Figure getFigure(Position from) {
        return desk.cellAt(from).getFigure();
    }

    private void validateMove(TaflMove move) throws InvalidMoveException {
        final Position step = validatedMoveStep(move);
        final Figure figure = desk.cellAt(move.from()).getFigure();
        validatePath(move, step, figure);
        if (!desk.contains(move.from())) {
            throw new InvalidMoveException("There is no figure at " + move.from());
        }
        //todo
    }

    private void validatePath(TaflMove move, Position step, Figure figure) throws InvalidMoveException {
        if (!isCellAvailableForTheFigure(move.to(), figure)) {
            throw new InvalidMoveException("target cell should be available to the figure");
        }

        for (
                Position current = move.from().add(step);
                current.equals(move.to());
                current = current.add(step)
        ) {
            if (desk.cellAt(current).hasFigure()) {
                throw new InvalidMoveException(
                        "there should be no other figures on the pass from the initial cell to the target cell");
            }

            if (!isCellAvailableForTheFigure(current, figure)) {
                throw new InvalidMoveException(
                        "All cells on the path from the initial cell to the final cell must be available to the current" +
                                "figure, but the cell " + current + " – isn't");
            }
        }
    }

    private Position validatedMoveStep(TaflMove move) throws InvalidMoveException {
        if (!desk.contains(move.from())) {
            throw new InvalidMoveException("initial cell is invalid");
        }

        if (!desk.cellAt(move.from()).hasFigure()) {
            throw new InvalidMoveException("initial cell is empty");
        }

        if (desk.cellAt(move.from()).getFigure().ownedBy() != getCurrentPlayer()) {
            throw new InvalidMoveException("figure in the initial cell is not owned by the current user");
        }

        if (!desk.contains(move.to())) {
            throw new InvalidMoveException("target cell is invalid");
        }

        if (desk.cellAt(move.to()).hasFigure()) {
            throw new InvalidMoveException("target cell is occupied");
        }

        if (move.from().getX() != move.to().getX() && move.from().getY() != move.to().getY()) {
            throw new InvalidMoveException("cells should be connected by a straight (vertical or horizontal) line");
        }

        final int moveDistance = Position.manhattanDistance(move.from(), move.to());

        if (moveDistance == 0) {
            throw new InvalidMoveException("cells should be different");
        }

        if (isFigureMoveDistanceRestricted() && moveDistance > 1) {
            throw new InvalidMoveException("move distance should be equal to one, because move distance is restricted");
        }

        return move.to().subtract(move.from()).divide(moveDistance);
    }
}
