package com.zinko.time_tracker.service.mapper;

import com.zinko.time_tracker.data.entity.Task;
import com.zinko.time_tracker.service.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    Task toTask(TaskDto taskDto);

    TaskDto toDto(Task task);
}
