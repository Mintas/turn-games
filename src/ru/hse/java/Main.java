package ru.hse.java;

import ru.hse.java.model.BaseFigure;
import ru.hse.java.model.Figure;
import ru.hse.java.model.Player;
import ru.hse.java.tafl.model.TaflFigureType;
import ru.hse.java.tafl.model.TaflFigures;

public class Main {
    public static void main(String[] args) {
        Figure defendor = TaflFigures.taflKing();
        Figure attacker = TaflFigures.taflWarrior(Player.BLACK);


        //Figure defendor = Move -> field.getCell(move.getTo()).hasFigure() ?
        // field.getCell(move.getTo()).getFigure ()

        if (defendor.getType() == TaflFigureType.KING) {
            //
        } else {
            //kill
        }
    }
}
