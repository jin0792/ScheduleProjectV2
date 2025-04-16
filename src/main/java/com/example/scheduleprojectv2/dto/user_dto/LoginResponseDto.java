package com.example.scheduleprojectv2.dto.user_dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final Long id;
    private final String email;
    private final String username;

    public LoginResponseDto(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

}
