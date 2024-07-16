package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Role;
import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.service.AuthService;
import com.zinko.time_tracker.service.JwtService;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.JwtResponse;
import com.zinko.time_tracker.service.dto.UserAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse signUp(UserAuthDto userAuthDto) {
        User user = User.builder()
                .email(userAuthDto.getEmail())
                .password(passwordEncoder.encode(userAuthDto.getPassword()))
                .role(Role.ROLE_EMPLOYEE)
                .build();
        userService.create(user);

        String token = jwtService.generateToken(user);
        return new JwtResponse(token);
    }

    @Override
    public JwtResponse signIn(UserAuthDto userAuthDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuthDto.getEmail(), userAuthDto.getPassword()));

        User user = (User) userService.userDetailsService().loadUserByUsername(userAuthDto.getEmail());
        String token = jwtService.generateToken(user);
        return new JwtResponse(token);
    }
}
