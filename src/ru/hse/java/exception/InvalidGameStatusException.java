package ru.hse.java.exception;

import ru.hse.java.model.Status;

public class InvalidGameStatusException extends Exception {
    private final Status status;

    public InvalidGameStatusException(Status status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return "Game is not ACTIVE, but in " + status + " state!";
    }
}
