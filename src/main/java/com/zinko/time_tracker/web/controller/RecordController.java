package com.zinko.time_tracker.web.controller;

import com.zinko.time_tracker.service.RecordService;
import com.zinko.time_tracker.service.dto.RecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class RecordController {
    private final RecordService recordService;

    @PostMapping("/task/{id}/start-record")
    public RecordDto startRecord(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id, @RequestBody RecordDto recordDto) {
        return recordService.create(userDetails.getUsername(), id, recordDto);
    }

    @PostMapping("/task/finish-record")
    public RecordDto finishRecord(@AuthenticationPrincipal UserDetails userDetails, @RequestBody RecordDto recordDto) {
        return recordService.finishRecord(userDetails.getUsername(), recordDto);
    }

    @GetMapping("/my-records")
    public List<RecordDto> getRecords(@AuthenticationPrincipal UserDetails userDetails) {
        return recordService.getRecordsByUserEmail(userDetails.getUsername());
    }

    @GetMapping("/task/{id}/records")
    public List<RecordDto> getRecords(@PathVariable Long id) {
        return recordService.getRecordsByTaskId(id);
    }

}
