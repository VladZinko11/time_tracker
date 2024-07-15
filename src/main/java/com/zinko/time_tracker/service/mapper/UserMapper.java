package com.zinko.time_tracker.service.mapper;

import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.service.dto.UserAuthDto;
import com.zinko.time_tracker.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(User user);

    User toUser(UserAuthDto userAuthDto);

    User toUser(UserDto userDto);
}
