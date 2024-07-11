package com.zinko.time_tracker.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RecordDto {
    private Long id;
    private LocalDateTime finishTime;
    private LocalDateTime startTime;
    private TaskDto task;
}
