package ru.hse.java.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Field implements Printable {
    private final int height;
    private final int width;
    private final List<Cell> field;

    public Field(int height, int width) {
        this.width = width;
        this.height = height;
        this.field = initField(height, width);
    }

    private static List<Cell> initField(int height, int width) {
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
        return field.get(position.getY() * width + position.getX());
    }

    private boolean isInRange(int coordinate, int limit) {
        return coordinate < limit && coordinate >= 0;
    }

    @Override
    public String getRepresentation() {
        //todo : implement
        return "printField";
    }
}
