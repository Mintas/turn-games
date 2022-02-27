package ru.hse.java.tictactoe;

import ru.hse.java.engine.DeskFactory;

public record TicTacToeDeskFactory(int size) implements DeskFactory<TictactoeDesk>{
    @Override
    public TictactoeDesk createDesk() {
        return new TictactoeDesk(size);
    }
}
