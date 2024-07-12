package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.dto.ErrorDto;
import com.zinko.time_tracker.service.dto.ValidationResultDto;
import com.zinko.time_tracker.service.exception.BadCredentialsException;
import com.zinko.time_tracker.service.exception.NotFoundException;
import com.zinko.time_tracker.service.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@Controller
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorController {
    private final ErrorService errorService;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto error(Exception e) {
        return new ErrorDto("Server error", "Oops, something wrong with server");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto error(NotFoundException e) {
        return new ErrorDto("Client error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto error(BadCredentialsException e) {
        return new ErrorDto("Client error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDto error(ValidationException e) {
        Map<String, List<String>> errors = errorService.mapErrors(e.getErrors());
        return new ValidationResultDto(errors);
    }
}
