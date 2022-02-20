package ru.hse.java.engine;

import ru.hse.java.exception.InvalidGameStatusException;
import ru.hse.java.exception.InvalidMoveException;
import ru.hse.java.model.Move;
import ru.hse.java.model.Player;
import ru.hse.java.model.Printable;
import ru.hse.java.model.Status;

public interface GameEngine extends Printable {
    Status getStatus();
    Player getCurrentPlayer();
    void makeMove(Move move) throws InvalidGameStatusException, InvalidMoveException;
}
