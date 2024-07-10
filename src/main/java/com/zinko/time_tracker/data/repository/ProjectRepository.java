package com.zinko.time_tracker.data.repository;

import com.zinko.time_tracker.data.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
