package com.zinko.time_tracker.service.dto;

import com.zinko.time_tracker.data.entity.Project;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Project}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto implements Serializable {
    private Long id;
    @NotBlank(message = "Project name must be not empty")
    private String name;
}