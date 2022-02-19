package ru.hse.java.model;

public interface Figure extends Printable {
    Player ownedBy();
    FigureType getType();
}
