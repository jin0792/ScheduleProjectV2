package com.example.scheduleprojectv2.dto.schedule_dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    @NotNull
    @Size(max = 10)
    private final String title;

    private final String contents;

    private final String username;

    public CreateScheduleRequestDto(String username, String title, String contents) {

        // 어떤 회원이 작성했는지 요청 정보에 담겨있어야 한다.
        // Cookie, Session, Token, Servlet Filter로 활용
        this.username = username;
        this.title = title;
        this.contents = contents;
    }
}
