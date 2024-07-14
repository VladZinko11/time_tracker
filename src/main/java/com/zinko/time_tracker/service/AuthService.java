package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.UserCreateDto;

public interface AuthService {
    JwtResponse signUp(UserCreateDto userCreateDto);

    JwtResponse signIn(UserCreateDto userCreateDto);
}
