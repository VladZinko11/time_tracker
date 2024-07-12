package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Project;
import com.zinko.time_tracker.data.repository.ProjectRepository;
import com.zinko.time_tracker.service.ProjectMapper;
import com.zinko.time_tracker.service.ProjectService;
import com.zinko.time_tracker.service.dto.ProjectDto;
import com.zinko.time_tracker.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public ProjectDto create(ProjectDto projectDto) {
        Project project = projectMapper.toProject(projectDto);

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
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("Not found Project with id: " + id);
        }
        projectRepository.deleteById(id);
    }


}
