package ru.hse.java.tafl;

import ru.hse.java.engine.AbstractGameEngine;
import ru.hse.java.exception.InvalidMoveException;
import ru.hse.java.model.*;
import ru.hse.java.tafl.model.TaflFigureType;

import java.util.List;

public class TaflGameEngine extends AbstractGameEngine {
    public TaflGameEngine(Player firstPlayer, Desk desk) {
        //todo : check desk size is correct to have center?
        super(firstPlayer, desk);
    }

    @Override
    protected void doMove(Move move) throws InvalidMoveException {
        validateMove(move);
        Figure figure = getFigure(move.from());
        desk.removeFigure(move.from());
        desk.setFigure(move.to(), figure);

        for (Position step : getSteps()) {
            Position enemyCandidate = move.to().add(step);
            if (isWarriorCaptured(enemyCandidate)) {
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
        //todo
        return false;
    }

    private boolean isKingCaptured(Position enemyCandidate) {
        //todo
        return false;
    }

    private boolean isWarriorCaptured(Position enemyCandidate) {
        //todo
        return false;
    }

    private Iterable<? extends Position> getSteps() {
        return List.of(
                new Position(0, 1),
                new Position(1, 0),
                new Position(0, -1),
                new Position(-1, 0));
    }

    private Figure getFigure(Position from) {
        return desk.cellAt(from).getFigure();
    }

    private void validateMove(Move move) throws InvalidMoveException {
        if (!desk.contains(move.from())) {
            throw new InvalidMoveException("There is no figure at " + move.from());
        }
        //todo
    }
}
