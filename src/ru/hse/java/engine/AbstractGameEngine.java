package ru.hse.java.engine;

import ru.hse.java.exception.InvalidGameStatusException;
import ru.hse.java.exception.InvalidMoveException;
import ru.hse.java.model.Desk;
import ru.hse.java.model.Move;
import ru.hse.java.model.Player;
import ru.hse.java.model.Status;
import ru.hse.java.tafl.TaflGameEngine;

import java.util.Objects;

public abstract class AbstractGameEngine implements GameEngine {
    private Status status;
    private Player currentPlayer;
    protected final Desk desk;

    public AbstractGameEngine(Player firstPlayer, Desk desk) {
        this.status = Status.ACTIVE;
        this.currentPlayer = firstPlayer;
        this.desk = desk;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status){
        Objects.requireNonNull(status);
        this.status = status;
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void makeMove(Move move) throws InvalidGameStatusException, InvalidMoveException {
        if (status != Status.ACTIVE) {
            throw new InvalidGameStatusException(status);
        }

        // here we use TemplateMethod patter to define exact Strategy how move will get performed
        doMove(move);

        if (status == Status.ACTIVE) {
            swapPlayer();
        }
    }

    private void swapPlayer() {
        this.currentPlayer = this.currentPlayer == Player.BLACK ? Player.WHITE : Player.BLACK;
    }

    /**
     * will update status if Draw or Win condition met
     * @param move to perform
     * @throws InvalidMoveException
     */
    protected abstract void doMove(Move move) throws InvalidMoveException;

    @Override
    public String getRepresentation() {
        // we can add status , player...
        return desk.getRepresentation();
    }
}
