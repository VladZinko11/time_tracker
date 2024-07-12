package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.ProjectService;
import com.zinko.time_tracker.service.dto.ProjectDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ErrorService errorService;

    @PostMapping("/create")
    public ProjectDto create(@RequestBody @Valid ProjectDto projectDto, Errors errors) {
        errorService.checkErrors(errors);
        return projectService.create(projectDto);
    }

    @GetMapping("/{id}")
    public ProjectDto getById(@PathVariable Long id) {
        return projectService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}
