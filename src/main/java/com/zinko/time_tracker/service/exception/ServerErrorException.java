package com.zinko.time_tracker.service.exception;

public class ServerErrorException extends TimeTrackerException {
    public ServerErrorException() {
        super();
    }

    public ServerErrorException(String message) {
        super(message);
    }

    public ServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
