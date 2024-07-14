package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.RecordDto;

import java.util.List;

public interface RecordService {
    RecordDto create(String userEmail, Long taskId, RecordDto recordDto);

    List<RecordDto> getRecordsByTaskId(Long taskId);

    List<RecordDto> getRecordsByUserEmail(String userEmail);

    RecordDto finishRecord(String userEmail, RecordDto recordDto);
}
