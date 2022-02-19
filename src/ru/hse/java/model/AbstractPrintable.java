package ru.hse.java.model;

public abstract class AbstractPrintable implements Printable {
    private final String representation;

    public AbstractPrintable(String representation) {
        this.representation = representation;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }
}
