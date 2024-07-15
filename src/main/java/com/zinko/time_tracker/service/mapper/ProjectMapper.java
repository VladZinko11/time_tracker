package com.zinko.time_tracker.service.mapper;

import com.zinko.time_tracker.data.entity.Project;
import com.zinko.time_tracker.service.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    Project toProject(ProjectDto projectDto);

    ProjectDto toDto(Project project);
}
