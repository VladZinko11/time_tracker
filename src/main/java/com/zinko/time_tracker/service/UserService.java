package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.UserDto;
import com.zinko.time_tracker.service.dto.UserCreateDto;

import java.util.List;

public interface UserService {
    UserDto create(UserCreateDto userCreateDto);

    UserDto getById(Long id);

    List<UserDto> getAll();


    UserDto update(UserDto userDto);

    void delete(Long id);
}
