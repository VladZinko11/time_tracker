package com.zinko.time_tracker.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private String name;
    private ProjectDto project;
}
