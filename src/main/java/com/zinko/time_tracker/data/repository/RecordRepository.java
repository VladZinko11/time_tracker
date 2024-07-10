package com.zinko.time_tracker.data.repository;

import com.zinko.time_tracker.data.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
