package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Record;
import com.zinko.time_tracker.data.repository.RecordRepository;
import com.zinko.time_tracker.service.RecordMapper;
import com.zinko.time_tracker.service.RecordService;
import com.zinko.time_tracker.service.dto.RecordDto;
import com.zinko.time_tracker.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordServerImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper;


    @Override
    public RecordDto create(RecordDto recordDto) {
        Record record = recordMapper.toRecord(recordDto);
        recordRepository.save(record);
        return recordMapper.toDto(record);
    }

    @Override
    public RecordDto getById(Long id) {
        Optional<Record> optionalRecord = recordRepository.findById(id);
        Record record = optionalRecord.orElseThrow(() -> new NotFoundException("Not found record with id: " + id));
        return recordMapper.toDto(record);
    }

    @Override
    public void delete(Long id) {
        if (!recordRepository.existsById(id)) {
            throw new NotFoundException("Not found record with id: " + id);
        }
        recordRepository.deleteById(id);
    }

    @Override
    public RecordDto update(RecordDto recordDto) {
        Record record = recordMapper.toRecord(recordDto);
        recordRepository.save(record);
        return recordMapper.toDto(record);
    }
}
