package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.TaskDto;

public interface TaskService {
    TaskDto create(TaskDto taskDto);

    TaskDto getById(Long id);

    void delete(Long id);
}
