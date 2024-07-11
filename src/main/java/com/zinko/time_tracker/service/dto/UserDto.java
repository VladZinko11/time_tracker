package com.zinko.time_tracker.service.dto;

import com.zinko.time_tracker.data.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.zinko.time_tracker.data.entity.User}
 */

@Setter
@Getter
public class UserDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private ProjectDto project;
    private List<RecordDto> records;
}