package com.zinko.time_tracker.service;

import com.zinko.time_tracker.data.entity.Record;
import com.zinko.time_tracker.service.dto.RecordDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    Record toRecord(RecordDto recordDto);

    RecordDto toDto(Record record);
}
