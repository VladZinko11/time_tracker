package com.zinko.time_tracker.service;

import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDto create(User user);

    UserDto getById(Long id);


    UserDto update(UserDto userDto, UserDetails userDetails);

    void delete(UserDetails userDetails);

    User getByEmail(String email);

    UserDetailsService userDetailsService();

    List<UserDto> getByProjectId(Long id);
}
