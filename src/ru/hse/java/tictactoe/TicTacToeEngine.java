package ru.hse.java.tictactoe;

import ru.hse.java.engine.AbstractGameEngine;
import ru.hse.java.engine.DeskFactory;
import ru.hse.java.exception.InvalidMoveException;
import ru.hse.java.model.Position;
import ru.hse.java.model.Status;
import ru.hse.java.tafl.model.TaflMove;
import ru.hse.java.model.Player;
import ru.hse.java.tictactoe.model.TicTacToeFigure;
import ru.hse.java.tictactoe.model.TicTacToeMove;

import static ru.hse.java.tictactoe.model.TicTacToeFigure.ticTacToeFigure;

public class TicTacToeEngine extends AbstractGameEngine<TicTacToeMove, TictactoeDesk> {
    public TicTacToeEngine(Player firstPlayer, DeskFactory<TictactoeDesk> deskFactory) {
        super(firstPlayer, deskFactory);
    }

    @Override
    protected void doMove(TicTacToeMove move) throws InvalidMoveException {
        validate(move);
        desk.setFigure(move.position(), ticTacToeFigure(getCurrentPlayer()));

        if (checkWin(move.position())) {
            setStatus(Status.WIN);
        } else if (checkDraw()) {
            setStatus(Status.DRAW);
        }
    }

    private boolean checkWin(Position position) {
        //todo
        return false;
    }

    private boolean checkDraw() {
        //todo
        return false;
    }

    private void validate(TicTacToeMove move) throws InvalidMoveException {
        if (!desk.contains(move.position())) {
            throw new InvalidMoveException("There is no cell on desk at " + move.position());
        }
        if (desk.cellAt(move.position()).hasFigure()) {
            throw new InvalidMoveException("Cell at " + move.position() + " is already occupied!");
        }
    }
}
