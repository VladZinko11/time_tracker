package com.zinko.time_tracker.service;

import com.zinko.time_tracker.data.entity.Project;
import com.zinko.time_tracker.service.dto.ProjectDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toProject(ProjectDto projectDto);

    ProjectDto toDto(Project project);
}
