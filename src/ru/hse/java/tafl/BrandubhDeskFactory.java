package ru.hse.java.tafl;

import ru.hse.java.engine.DeskFactory;
import ru.hse.java.model.Desk;
import ru.hse.java.model.Player;
import ru.hse.java.model.Position;
import ru.hse.java.tafl.TaflDesk;
import ru.hse.java.tafl.model.TaflFigures;

import static ru.hse.java.model.Position.getSteps;

public class BrandubhDeskFactory implements DeskFactory<TaflDesk> {
    @Override
    public TaflDesk createDesk() {
        TaflDesk desk = new TaflDesk(7);
        Position center = desk.getCenter();
        desk.setFigure(center, TaflFigures.taflKing());

        for (Position step : getSteps()) {
            desk.setFigure(center.add(step), TaflFigures.taflWarrior(Player.WHITE));
            desk.setFigure(center.add(step.scale(2)), TaflFigures.taflWarrior(Player.BLACK));
            desk.setFigure(center.add(step.scale(3)), TaflFigures.taflWarrior(Player.BLACK));
        }
        return desk;
    }
}
