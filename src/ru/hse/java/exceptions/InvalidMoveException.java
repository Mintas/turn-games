package ru.hse.java.exceptions;

public class InvalidMoveException extends Exception {
    private final String reason;

    public InvalidMoveException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
