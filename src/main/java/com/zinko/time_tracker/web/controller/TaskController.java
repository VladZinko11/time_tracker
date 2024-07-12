package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.TaskService;
import com.zinko.time_tracker.service.dto.TaskDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ErrorService errorService;

    @PostMapping("/add")
    public TaskDto create(@RequestBody @Valid TaskDto taskDto, Errors errors) {
        errorService.checkErrors(errors);
        return taskService.create(taskDto);
    }

    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

}
