package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto create(ProjectDto projectDto, String userEmail);

    void delete(Long id, String userEmail);

    ProjectDto getById(Long id);

    List<ProjectDto> getByUserEmail(String userEmail);
}
