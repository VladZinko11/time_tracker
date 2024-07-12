package com.zinko.time_tracker.service.exception;

import lombok.Getter;
import org.springframework.validation.Errors;

public class ValidationException extends TimeTrackerException {
    @Getter
    private final Errors errors;

    public ValidationException(Errors errors) {
        this.errors = errors;
    }
}
