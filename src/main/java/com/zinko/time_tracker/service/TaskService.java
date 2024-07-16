package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto create(Long projectId, TaskDto taskDto);

    TaskDto getById(Long id);

    void delete(Long id);

    List<TaskDto> getByProjectId(Long projectId);

    boolean existsById(Long taskId);
}
