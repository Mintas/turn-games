package ru.hse.java.engine;

import ru.hse.java.exceptions.InvalidGameStatusException;
import ru.hse.java.exceptions.InvalidMoveException;
import ru.hse.java.model.Desk;
import ru.hse.java.model.Move;
import ru.hse.java.model.Player;
import ru.hse.java.model.Status;

public abstract class AbstractGameEngine implements GameEngine {
    private Status status;
    private Player currentPlayer;
    protected final Desk gameDesk;

    public AbstractGameEngine(Player firstPlayer, Desk gameDesk) {
        this.gameDesk = gameDesk;
        this.status = Status.ACTIVE;
        this.currentPlayer = firstPlayer;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
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

        //here we use Pattern named TemplateMethod
        doMove(move);

        if (status == Status.ACTIVE) {
            swapPlayer();
        }
    }

    private void swapPlayer() {
        currentPlayer = currentPlayer == Player.BLACK ? Player.WHITE : Player.BLACK;
    }

    /**
     * introduces exact Strategy of how the move is done...
     * @param move to be done
     *             switches status if move leads to DRAW or WIN
     */
    protected abstract void doMove(Move move) throws InvalidMoveException;

    @Override
    public String getRepresentation() {
        //we can add: status, currentPlayer...
        return gameDesk.getRepresentation();
    }
}
