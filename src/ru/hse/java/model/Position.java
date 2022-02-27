package ru.hse.java.model;

import java.util.List;

public class Position {
    public static final Position ZERO_COORDINATE = new Position(0, 0);
    //x 0, y 1
    private final int x;
    private final int y;

    public Position(int x, int y) { //final modifier on parameter method makes it immutable
        this.x = x;
        this.y = y;
    }

    public static int manhattanDistance(Position to, Position center) {
        return Math.abs(to.getX() - center.getX()) + Math.abs(to.getY() - center.getY()) ;
    }

    public static Iterable<? extends Position> getSteps() {
        return List.of(
                new Position(0, 1),
                new Position(1, 0),
                new Position(0, -1),
                new Position(-1, 0));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position add(Position second) {
        return new Position(x + second.x,  y + second.y);
    }
    public Position subtract(final Position other) {  return new Position(x - other.x, y - other.y); }
    public Position scale(int scale) {
        return new Position(x * scale, y * scale);
    }
    public Position divide(final int coefficient) {  return new Position(x / coefficient, y / coefficient); }
}

// Value classes, Active classes ->
