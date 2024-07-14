package com.zinko.time_tracker.service.impl;

import com.zinko.time_tracker.data.entity.Record;
import com.zinko.time_tracker.data.entity.Task;
import com.zinko.time_tracker.data.entity.User;
import com.zinko.time_tracker.data.repository.RecordRepository;
import com.zinko.time_tracker.data.repository.TaskRepository;
import com.zinko.time_tracker.service.RecordService;
import com.zinko.time_tracker.service.UserService;
import com.zinko.time_tracker.service.dto.RecordDto;
import com.zinko.time_tracker.service.exception.NotFoundException;
import com.zinko.time_tracker.service.mapper.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecordServerImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper;
    private final TaskRepository taskRepository;
    private final UserService userService;


    @Override
    public RecordDto create(String userEmail, Long taskId, RecordDto recordDto) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        Task task = optionalTask.orElseThrow(
                () -> new NotFoundException("Not found task with id: " + taskId));
        Record record = recordMapper.toRecord(recordDto);
        record.setTask(task);
        User user = userService.getByEmail(userEmail);
        record.setUser(user);
        record.setStartTime(LocalDateTime.now());
        recordRepository.save(record);
        return recordMapper.toDto(record);
    }

    @Override
    public List<RecordDto> getRecordsByTaskId(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new NotFoundException("Not found record with id: " + taskId);
        }
        List<Record> records = recordRepository.findByTaskId(taskId);
        return records.stream().map(recordMapper::toDto).map(this::recordSetTime).toList();
    }

    @Override
    public List<RecordDto> getRecordsByUserEmail(String userEmail) {
        User user = userService.getByEmail(userEmail);
        List<Record> records = recordRepository.findByUserId(user.getId());
        return records.stream().map(recordMapper::toDto).map(this::recordSetTime).toList();
    }

    @Override
    public RecordDto finishRecord(String userEmail, RecordDto recordDto) {
        User user = userService.getByEmail(userEmail);
        List<Record> records = recordRepository.findByUserId(user.getId());
        Record record = records.get(records.size() - 1);
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
