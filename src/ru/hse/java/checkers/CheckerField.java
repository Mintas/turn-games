package ru.hse.java.checkers;

import ru.hse.java.model.Cell;
import ru.hse.java.model.Field;

import java.util.ArrayList;
import java.util.List;

public class CheckerField extends Field {
    private static final int MAX_HEIGHT = 8;
    private static final int MAX_WIDTH = 8;

    public CheckerField() {
        super(MAX_HEIGHT, MAX_WIDTH);
    }


}
