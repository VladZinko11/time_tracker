package com.zinko.time_tracker.data.repository;

import com.zinko.time_tracker.data.entity.Project;
import com.zinko.time_tracker.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllUsersByProject(Project project);
    Optional<User> findByEmail(String email);

}
