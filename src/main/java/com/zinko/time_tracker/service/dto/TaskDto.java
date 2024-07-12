package com.zinko.time_tracker.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for {@link com.zinko.time_tracker.data.entity.Task}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    @NotBlank(message = "Task name must be not empty")
    private String name;
    private List<RecordDto> records;
    private ProjectDto project;
}
