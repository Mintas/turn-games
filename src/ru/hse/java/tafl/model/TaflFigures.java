package ru.hse.java.tafl.model;

import ru.hse.java.model.BaseFigure;
import ru.hse.java.model.Player;

public class TaflFigures {
    public static BaseFigure taflKing() {
        return new BaseFigure(Player.WHITE, "@", TaflFigureType.KING);
    }

    public static BaseFigure taflWarrior(Player owner) {
        return new BaseFigure(owner, owner == Player.BLACK ? "◆" : "◇", TaflFigureType.WARRIOR);
    }
}
