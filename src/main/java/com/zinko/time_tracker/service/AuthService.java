package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.JwtResponse;
import com.zinko.time_tracker.service.dto.UserAuthDto;

public interface AuthService {
    JwtResponse signUp(UserAuthDto userAuthDto);

    JwtResponse signIn(UserAuthDto userAuthDto);
}
