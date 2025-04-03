package com.example.scheduleprojectv2.dto.user_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    private String email;

    private String password;
}
