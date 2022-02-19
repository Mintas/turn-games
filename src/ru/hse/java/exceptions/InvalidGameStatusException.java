package ru.hse.java.exceptions;

import ru.hse.java.model.Status;

public class InvalidGameStatusException extends Exception {
    private final Status status;

    public InvalidGameStatusException(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return "Game is in " + status + " status";
    }
}
