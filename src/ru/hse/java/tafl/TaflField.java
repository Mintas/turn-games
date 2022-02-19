package ru.hse.java.tafl;

import ru.hse.java.model.Cell;
import ru.hse.java.model.Field;

import java.util.List;

public class TaflField extends Field {
    private static final int TAFL_SIZE = 13;

    public TaflField() {
        super(TAFL_SIZE, TAFL_SIZE);
    }
}
