package ru.hse.java.exception;

public class InvalidMoveException extends Exception {
    private final String reason;

    public InvalidMoveException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
