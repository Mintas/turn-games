package ru.hse.java.model;

public class Position {
    public static final Position ZERO_COORDINATE = new Position(0, 0);
    //x 0, y 1
    private final int x;
    private final int y;

    public Position(int x, int y) { //final modifier on parameter method makes it immutable
        this.x = x;
        this.y = y;
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

    public Position scale(int scale) {
        return new Position(x * scale, y * scale);
    }

}

// Value classes, Active classes ->
