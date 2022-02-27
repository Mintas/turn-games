package ru.hse.java.tafl;

import ru.hse.java.model.Desk;
import ru.hse.java.model.Position;

public class TaflDesk extends Desk {
    private final Position center;

    public TaflDesk(int size) {
        super(size, size);
        this.center = new Position(size / 2, size / 2);
    }

    public Position getCenter() {
        return center;
    }

    public boolean isCornerCell(Position position) {
        return Position.manhattanDistance(position, center) == width/2 + height/2;
    }

    public boolean isCenterCell(Position position) {
        return Position.manhattanDistance(position, center) == 0;
    }

    @Override
    protected String emptyCellPresentation(Position position) {
        return isCornerCell(position) || isCenterCell(position) ? "▪"
                : super.emptyCellPresentation(position);
    }
}
