package ru.hse.java.model;

import java.util.Objects;

public class Cell {
    private final Figure figure;

    private Cell(Figure figure) {
        this.figure = figure;
    }

    public static Cell fill(Figure figure) {
        Objects.requireNonNull(figure, "Cannot fill cell with null figure");
        return new Cell(figure);
    }

    public static Cell clean() {
        return new Cell(null);
    }

    public boolean hasFigure() {
        return figure != null;
    }

    public Figure getFigure() {
        return figure;
    }
}
