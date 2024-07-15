package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Record;
import com.zinko.time_tracker.data.entity.Task;
import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.data.repository.RecordRepository;
import com.zinko.time_tracker.service.RecordService;
import com.zinko.time_tracker.service.TaskService;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.RecordDto;
import com.zinko.time_tracker.service.dto.TaskDto;
import com.zinko.time_tracker.service.exception.NotFoundException;
import com.zinko.time_tracker.service.mapper.RecordMapper;
import com.zinko.time_tracker.service.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper;
    private final TaskMapper taskMapper;
    private final TaskService taskService;
    private final UserService userService;


    @Override
    public RecordDto create(String userEmail, Long taskId) {
        TaskDto taskDto = taskService.getById(taskId);
        Task task = taskMapper.toTask(taskDto);
        Record record = new Record();
        record.setTask(task);
        User user = userService.getByEmail(userEmail);
        record.setUser(user);
        record.setStartTime(LocalDateTime.now());
        recordRepository.save(record);
        return recordMapper.toDto(record);
    }

    @Override
    public List<RecordDto> getRecordsByTaskId(Long taskId) {
        if (!taskService.existsById(taskId)) {
            throw new NotFoundException("Not found record with id: " + taskId);
        }
        List<Record> records = recordRepository.findByTaskId(taskId);
        return records.stream()
                .map(recordMapper::toDto)
                .map(this::recordSetTime)
                .toList();
    }

    @Override
    public List<RecordDto> getRecordsByUserEmail(String userEmail) {
        User user = userService.getByEmail(userEmail);
        List<Record> records = recordRepository.findByUserId(user.getId());
        return records.stream()
                .map(recordMapper::toDto)
                .map(this::recordSetTime)
                .toList();
    }

    @Override
    public RecordDto finishRecord(String userEmail, RecordDto recordDto) {
        User user = userService.getByEmail(userEmail);
        List<Record> records = recordRepository.findByUserId(user.getId());
        Record record = records.stream()
                .max(Comparator.comparing(Record::getStartTime))
                .orElseThrow(NotFoundException::new);
        record.setFinishTime(LocalDateTime.now());
        recordRepository.save(record);
        return recordMapper.toDto(record);
    }

    private RecordDto recordSetTime(RecordDto recordDto) {
        LocalDateTime finishTime = recordDto.getFinishTime() == null ? LocalDateTime.now() : recordDto.getFinishTime();
        recordDto.setTime(finishTime.getSecond() - recordDto.getStartTime().getSecond());
        return recordDto;
    }


}
