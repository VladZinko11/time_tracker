package com.zinko.time_tracker.service;

import com.zinko.time_tracker.service.dto.RecordDto;

public interface RecordService {
    RecordDto create(RecordDto recordDto);

    RecordDto getById(Long id);

    void delete(Long id);

    RecordDto update(RecordDto recordDto);
}
