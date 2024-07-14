package com.zinko.time_tracker.data.repository;

import com.zinko.time_tracker.data.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM records WHERE task_id=:taskId")
    List<Record> findByTaskId(Long taskId);

    @Query(nativeQuery = true, value = "SELECT * FROM records WHERE user_id=:userId")
    List<Record> findByUserId(Long userId);
}
