package ru.hse.java.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Desk implements Printable {
    protected final int height;
    protected final int width;
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

    public List<List<Position>> asLines() {
        List<List<Position>> lines = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Position> horizontal = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                horizontal.add(new Position(x, y));
            }
            lines.add(horizontal);
        }
        return lines;
    }

    private boolean isInRange(int coordinate, int limit) {
        return coordinate < limit && coordinate >= 0;
    }

    @Override
    public String getRepresentation() {
        StringBuilder stringBuilder = new StringBuilder();
        List<List<Position>> lists = asLines();
        for (List<Position> list : lists) {
            for (Position position : list) {
                Cell cell = cellAt(position);
                String cellPresentation = cell.hasFigure() ?
                        cell.getFigure().getRepresentation() :
                        //template method
                        emptyCellPresentation(position);
                stringBuilder.append(cellPresentation);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    protected String emptyCellPresentation(Position position) {
        return "▫";
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
}
