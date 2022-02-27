package ru.hse.java.engine;

import ru.hse.java.model.Desk;

public interface DeskFactory<D extends Desk> {
    D createDesk();
}
