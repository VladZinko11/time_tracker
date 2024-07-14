package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Project;
import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.data.repository.ProjectRepository;
import com.zinko.time_tracker.service.ProjectService;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.ProjectDto;
import com.zinko.time_tracker.service.exception.NotFoundException;
import com.zinko.time_tracker.service.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserService userService;

    @Override
    public ProjectDto create(ProjectDto projectDto, String userEmail) {
        Project project = projectMapper.toProject(projectDto);
        User user = userService.getByEmail(userEmail);
        project.setUserCreator(user);
        projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto getById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        Project project = optionalProject.orElseThrow(
                () -> new NotFoundException("Not found Project with id: " + id));
        return projectMapper.toDto(project);
    }

    @Override
    public List<ProjectDto> getByUserEmail(String userEmail) {
        User user = userService.getByEmail(userEmail);
        List<Project> projects = projectRepository.findByUserCreator(user);
        return projects.stream().map(projectMapper::toDto).toList();
    }

    @Override
    public void delete(Long id, String userEmail) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("Not found Project with id: " + id);
        }
        User user = userService.getByEmail(userEmail);
        projectRepository.deleteByIdAndUserCreator(id, user);
    }


}
