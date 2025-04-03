package com.example.scheduleprojectv2.controller;

import com.example.scheduleprojectv2.dto.schedule_dto.CreateScheduleRequestDto;
import com.example.scheduleprojectv2.dto.schedule_dto.ScheduleResponseDto;
import com.example.scheduleprojectv2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody CreateScheduleRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getUsername()
                );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long scheduleId) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(scheduleId);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody CreateScheduleRequestDto requestDto
    ) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(
                scheduleId, requestDto.getTitle(), requestDto.getContents()
        );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {

        scheduleService.deleteSchedule(scheduleId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}

