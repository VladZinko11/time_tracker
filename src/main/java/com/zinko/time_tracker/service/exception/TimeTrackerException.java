package com.zinko.time_tracker.service.exception;

public class TimeTrackerException extends RuntimeException {
    public TimeTrackerException() {
        super();
    }

    public TimeTrackerException(String message) {
        super(message);
    }

    public TimeTrackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
