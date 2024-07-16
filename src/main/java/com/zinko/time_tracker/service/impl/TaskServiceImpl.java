package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Project;
import com.zinko.time_tracker.data.entity.Task;
import com.zinko.time_tracker.data.repository.TaskRepository;
import com.zinko.time_tracker.service.ProjectService;
import com.zinko.time_tracker.service.TaskService;
import com.zinko.time_tracker.service.dto.ProjectDto;
import com.zinko.time_tracker.service.dto.TaskDto;
import com.zinko.time_tracker.service.exception.NotFoundException;
import com.zinko.time_tracker.service.mapper.ProjectMapper;
import com.zinko.time_tracker.service.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @Override
    public TaskDto create(Long projectId, TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
        ProjectDto projectDto = projectService.getById(projectId);
        Project project = projectMapper.toProject(projectDto);
        task.setProject(project);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    public TaskDto getById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.orElseThrow(() -> new NotFoundException("Not found Task with id: " + id));
        return taskMapper.toDto(task);
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Not found Task with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskDto> getByProjectId(Long projectId) {
        if (!projectService.existsById(projectId)) {
            throw new NotFoundException("Not found project with id: " + projectId);
        }
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        return tasks.stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Override
    public boolean existsById(Long taskId) {
        return taskRepository.existsById(taskId);
    }
}



