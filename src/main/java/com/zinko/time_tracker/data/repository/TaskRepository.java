package com.zinko.time_tracker.data.repository;

import com.zinko.time_tracker.data.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
