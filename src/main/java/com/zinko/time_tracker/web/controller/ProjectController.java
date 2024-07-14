package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.ErrorService;
import com.zinko.time_tracker.service.ProjectService;
import com.zinko.time_tracker.service.dto.ProjectDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ErrorService errorService;

    @PostMapping("/create")
    public ProjectDto create(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid ProjectDto projectDto, Errors errors) {
        errorService.checkErrors(errors);
        return projectService.create(projectDto, userDetails.getUsername());
    }

    @GetMapping("/my-projects")
    public List<ProjectDto> getProjectsByUser(@AuthenticationPrincipal UserDetails userDetails) {
        return projectService.getByUserEmail(userDetails.getUsername());
    }

    @GetMapping("/{id}")
    public ProjectDto getById(@PathVariable Long id) {
        return projectService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        projectService.delete(id, userDetails.getUsername());
    }
}
