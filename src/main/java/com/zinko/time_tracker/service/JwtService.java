package com.zinko.time_tracker.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token);
}
