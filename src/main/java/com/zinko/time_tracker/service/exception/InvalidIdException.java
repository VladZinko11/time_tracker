package com.zinko.time_tracker.service.exception;

public class InvalidIdException extends RuntimeException {
    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
