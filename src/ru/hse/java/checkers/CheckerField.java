package ru.hse.java.checkers;

import ru.hse.java.model.Desk;

public class CheckerField extends Desk {
    private static final int MAX_HEIGHT = 8;
    private static final int MAX_WIDTH = 8;

    public CheckerField() {
        super(MAX_HEIGHT, MAX_WIDTH);
    }


}
