package com.zinko.time_tracker.service.dto;

import com.zinko.time_tracker.data.entity.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.zinko.time_tracker.data.entity.User}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    private Role role;
    private ProjectDto project;
    private List<RecordDto> records;
}