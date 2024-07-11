package com.zinko.time_tracker.service;

import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.service.dto.UserDto;
import com.zinko.time_tracker.service.dto.UserDtoCreate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toUser(UserDtoCreate userDtoCreate);

    User toUser(UserDto userDto);
}
