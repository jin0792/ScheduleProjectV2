package com.example.scheduleprojectv2.dto.user_dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotNull
    @Size(max = 4)
    private final String username;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") // 영문, 대소문자, 숫자를 포함한 1자 이상
    private final String email;

    private final String password;

    public SignUpRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
