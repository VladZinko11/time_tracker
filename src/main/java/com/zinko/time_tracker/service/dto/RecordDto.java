package com.zinko.time_tracker.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordDto {
    private Long id;
    private LocalDateTime finishTime;
    private LocalDateTime startTime;
    private Integer time;
    private TaskDto task;

}
