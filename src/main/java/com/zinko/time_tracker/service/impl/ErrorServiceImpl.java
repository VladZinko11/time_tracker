package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ErrorServiceImpl implements ErrorService {
    @Override
    public void checkErrors(Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
    }

    @Override
    public Map<String, List<String>> mapErrors(Errors Errors) {
        return Errors.getFieldErrors()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                        )
                );
    }
}
