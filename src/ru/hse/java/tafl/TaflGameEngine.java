package ru.hse.java.tafl;

import ru.hse.java.engine.AbstractGameEngine;
import ru.hse.java.exceptions.InvalidMoveException;
import ru.hse.java.model.*;
import ru.hse.java.tafl.model.TaflFigureType;

public class TaflGameEngine extends AbstractGameEngine {
    public TaflGameEngine(Player firstPlayer, Desk gameDesk) {
        super(firstPlayer, gameDesk);
    }

    @Override
    protected void doMove(Move move) throws InvalidMoveException {
        validateMove(move);
        Figure figure = getFigure(move);
        gameDesk.removeFigure(move.from());
        gameDesk.setFigure(move.to(), figure);

        for (Position step : getSteps()) {
            Position candidate = move.to().add(step);

            if (isWarriorCaptured(candidate)) {
                gameDesk.removeFigure(candidate);
            }

            if (isKingCaptured(candidate)) {
                setStatus(Status.WIN);
                return;
            }
        }

        if (gameDesk.isCornerCell(move.to()) && figure.getType() == TaflFigureType.KING ||
                enemyHasNoMoves(getCurrentPlayer())) {
            setStatus(Status.WIN);
        }

    }

    private boolean enemyHasNoMoves(Player currentPlayer) {
        return false;
    }

    //todo : implement
    private boolean isKingCaptured(Position candidate) {
        return false;
    }


    private boolean isWarriorCaptured(Position candidate) {
        return false;
    }

    private Iterable<? extends Position> getSteps() {
        return null;
    }

    private Figure getFigure(Move move) {
        return gameDesk.cellAt(move.from()).getFigure();
    }

    private void validateMove(Move move) {
        //gameDesk.hasCell() ...

    }
}
