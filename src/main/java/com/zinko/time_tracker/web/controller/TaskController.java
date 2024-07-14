package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.TaskService;
import com.zinko.time_tracker.service.dto.TaskDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ErrorService errorService;

    @PostMapping("/project/{id}/add-task")
    public TaskDto create(@PathVariable Long id, @RequestBody @Valid TaskDto taskDto, Errors errors) {
        errorService.checkErrors(errors);
        return taskService.create(id, taskDto);
    }

    @DeleteMapping("/delete/{taskId}")
    public void delete(@PathVariable Long taskId) {
        taskService.delete(taskId);
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @GetMapping("/project/{id}/all")
    public List<TaskDto> getByProjectId(@PathVariable Long id) {
        return taskService.getByProjectId(id);
    }
}
