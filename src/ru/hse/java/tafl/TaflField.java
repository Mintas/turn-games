package ru.hse.java.tafl;

import ru.hse.java.model.Desk;

public class TaflField extends Desk {
    private static final int TAFL_SIZE = 13;

    public TaflField() {
        super(TAFL_SIZE, TAFL_SIZE);
    }
}
