package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.AuthService;
import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.dto.JwtResponse;
import com.zinko.time_tracker.service.dto.UserAuthDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ErrorService errorService;

    @PostMapping("/sign-up")
    public JwtResponse signUp(@RequestBody @Valid UserAuthDto userAuthDto, Errors errors) {
        errorService.checkErrors(errors);
        return authService.signUp(userAuthDto);
    }

    @PostMapping("/sign-in")
    public JwtResponse signIn(@RequestBody @Valid UserAuthDto userAuthDto, Errors errors) {
        errorService.checkErrors(errors);
        return authService.signIn(userAuthDto);
    }
}