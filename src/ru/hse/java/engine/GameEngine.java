package ru.hse.java.engine;

import ru.hse.java.exception.InvalidGameStatusException;
import ru.hse.java.exception.InvalidMoveException;
import ru.hse.java.model.*;

public interface GameEngine<M extends Move> extends Printable {
    Status getStatus();
    Player getCurrentPlayer();
    void makeMove(M move) throws InvalidGameStatusException, InvalidMoveException;
}
