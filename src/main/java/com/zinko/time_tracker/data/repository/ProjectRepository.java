package com.zinko.time_tracker.data.repository;

import com.zinko.time_tracker.data.entity.Project;
import com.zinko.time_tracker.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByUserCreator(User user);

    void deleteByIdAndUserCreator(Long id, User user);
}
