package com.zinko.time_tracker.service.exception;

public class NotAuthorityException extends TimeTrackerException {

    public NotAuthorityException() {
        super();
    }

    public NotAuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthorityException(String s) {
    }
}
