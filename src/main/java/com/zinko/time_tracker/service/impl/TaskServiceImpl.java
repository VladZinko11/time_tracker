package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Task;
import com.zinko.time_tracker.data.repository.TaskRepository;
import com.zinko.time_tracker.service.TaskMapper;
import com.zinko.time_tracker.service.TaskService;
import com.zinko.time_tracker.service.dto.TaskDto;
import com.zinko.time_tracker.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDto create(TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
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


}



