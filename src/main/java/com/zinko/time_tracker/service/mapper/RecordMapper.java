package com.zinko.time_tracker.service.mapper;

import com.zinko.time_tracker.data.entity.Record;
import com.zinko.time_tracker.service.dto.RecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RecordMapper {
    Record toRecord(RecordDto recordDto);

    RecordDto toDto(Record record);
}
