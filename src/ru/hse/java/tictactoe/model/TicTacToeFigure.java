package ru.hse.java.tictactoe.model;

import ru.hse.java.model.BaseFigure;
import ru.hse.java.model.FigureType;
import ru.hse.java.model.Player;

public class TicTacToeFigure {
    enum Type implements FigureType {
        TICTACTOE
    }

    public static BaseFigure ticTacToeFigure(Player player) {
        return new BaseFigure(player, player == Player.WHITE ? "X" : "O", Type.TICTACTOE);
    }
}
