package ru.hse.java.model;

public class BaseFigure extends AbstractPrintable implements Figure {
    private final Player owner;
    private final FigureType type;

    public BaseFigure(Player owner, String representation, FigureType type) {
        super(representation);
        this.owner = owner;
        this.type = type;
    }

    @Override
    public Player ownedBy() {
        return owner;
    }

    @Override
    public FigureType getType() {
        return type;
    }
}
