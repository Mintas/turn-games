package ru.hse.java.tictactoe.model;

import ru.hse.java.model.Move;
import ru.hse.java.model.Position;

public record TicTacToeMove (Position position) implements Move {
}
