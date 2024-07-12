package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.ProjectDto;

public interface ProjectService {
    ProjectDto create(ProjectDto projectDto);

    void delete(Long id);

    ProjectDto getById(Long id);
}
