package com.zinko.time_tracker.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.zinko.time_tracker.data.entity.User}
 */
@Getter
@Setter
public class UserDtoCreate implements Serializable {
    private String email;
    private String password;
}