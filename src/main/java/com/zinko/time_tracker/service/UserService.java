package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.UserDto;
import com.zinko.time_tracker.service.dto.UserDtoCreate;

import java.util.List;

public interface UserService {
    UserDto create(UserDtoCreate userDtoCreate);

    UserDto get(Long id);

    List<UserDto> getAll();

    List<UserDto> getByProjectId(Long id);

    UserDto update(UserDto userDto);

    void delete(Long id);
}
