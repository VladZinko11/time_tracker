package com.zinko.time_tracker.service.exception;

public class BadCredentialsException extends TimeTrackerException {
    public BadCredentialsException() {
        super();
    }

    public BadCredentialsException(String message) {
        super(message);
    }

    public BadCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
