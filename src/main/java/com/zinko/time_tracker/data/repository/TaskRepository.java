package com.zinko.time_tracker.data.repository;

import com.zinko.time_tracker.data.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM tasks WHERE project_id=:projectId")
    List<Task> findByProjectId(Long projectId);
}
