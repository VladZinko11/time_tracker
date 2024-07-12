package com.zinko.time_tracker.service;

import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.service.dto.UserCreateDto;
import com.zinko.time_tracker.service.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toUser(UserCreateDto userCreateDto);

    User toUser(UserDto userDto);
}
