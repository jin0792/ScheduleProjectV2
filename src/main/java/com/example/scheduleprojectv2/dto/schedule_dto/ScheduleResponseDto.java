package com.example.scheduleprojectv2.dto.schedule_dto;

import com.example.scheduleprojectv2.entity.ScheduleEntity;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String title;

    private final String contents;

    public ScheduleResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    // 정적 팩토리 메소드
    public static ScheduleResponseDto toDto(ScheduleEntity schedule) {
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }
}
