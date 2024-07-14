package com.zinko.time_tracker.data.repository;

import com.zinko.time_tracker.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM users u JOIN projects_users pu on u.id = pu.user_id WHERE pu.project_id=:projectId")
    List<User> findByProjectId(Long projectId);
}
