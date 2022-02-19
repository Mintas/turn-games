package ru.hse.java.model;

public interface GameEngine extends Printable{
    Status getStatus();
    Player getCurrentPlayer();
    boolean makeMove(Move move);
}
