package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Role;
import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.service.AuthService;
import com.zinko.time_tracker.service.JwtResponse;
import com.zinko.time_tracker.service.JwtService;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.UserCreateDto;
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

    public JwtResponse signUp(UserCreateDto userCreateDto) {
        User user = User.builder()
                .email(userCreateDto.getEmail())
                .password(passwordEncoder.encode(userCreateDto.getPassword()))
                .role(Role.ROLE_EMPLOYEE)
                .build();
        userService.create(user);

        String token = jwtService.generateToken(user);
        return new JwtResponse(token);
    }

    @Override
    public JwtResponse signIn(UserCreateDto userCreateDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userCreateDto.getEmail(), userCreateDto.getPassword()));

        User user = (User) userService.userDetailsService().loadUserByUsername(userCreateDto.getEmail());
        String token = jwtService.generateToken(user);
        return new JwtResponse(token);
    }
}
