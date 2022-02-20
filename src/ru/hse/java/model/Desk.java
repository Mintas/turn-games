package ru.hse.java.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Desk implements Printable {
    private final int height;
    private final int width;
    private final List<Cell> desk; // [][] -> [{y = 0, x = 0}, {y = 0, x = 1}, ..... ]

    public Desk(int height, int width) {
        this.width = width;
        this.height = height;
        this.desk = initDesk(height, width);
    }

    private static List<Cell> initDesk(int height, int width) {
        List<Cell> asList = new ArrayList<>();
        for (int i = 0; i < height * width; i++) {
            asList.add(Cell.clean());
        }
        return asList;
    }

    public boolean contains(Position position) {
        return isInRange(position.getX(), width) &&
                isInRange(position.getY(), height);
    }

    public Cell cellAt(Position position) {
        //todo : checks?
        return desk.get(cellIndex(position));
    }

    private boolean isInRange(int coordinate, int limit) {
        return coordinate < limit && coordinate >= 0;
    }

    @Override
    public String getRepresentation() {
        //todo : implement
        return "printField";
    }

    public void removeFigure(Position from) {
        desk.set(cellIndex(from), Cell.clean());
    }

    public void setFigure(Position to, Figure figure) {
        desk.set(cellIndex(to), Cell.fill(figure));
    }

    private int cellIndex(Position position) {
        return position.getY() * width + position.getX();
    }

    public boolean isCornerCell(Position to) {
        Position center = getCenter();
        return Position.manhattanDistance(to, center) == width/2 + height/2;
    }

    private Position getCenter() {
        //todo
        return null;
    }
}
