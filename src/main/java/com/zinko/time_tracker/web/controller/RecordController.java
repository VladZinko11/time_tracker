package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.RecordService;
import com.zinko.time_tracker.service.dto.RecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("records")
public class RecordController {
    private final RecordService recordService;

    @PostMapping("/create")
    public RecordDto create(@RequestBody RecordDto recordDto) {
        return recordService.create(recordDto);
    }

    @GetMapping("/{id}")
    public RecordDto getById(@PathVariable Long id) {
        return recordService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        recordService.delete(id);
    }

    @PutMapping("/update")
    public RecordDto update(@RequestBody RecordDto recordDto) {
        return recordService.update(recordDto);
    }
}
