package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.AuthService;
import com.zinko.time_tracker.service.JwtResponse;
import com.zinko.time_tracker.service.dto.UserCreateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public JwtResponse signUp(@RequestBody @Valid UserCreateDto userCreateDto) {
        return authService.signUp(userCreateDto);
    }

    @PostMapping("/sign-in")
    public JwtResponse signIn(@RequestBody @Valid UserCreateDto userCreateDto) {
        return authService.signIn(userCreateDto);
    }
}
