package com.example.scheduleprojectv2.dto.user_dto;

import com.example.scheduleprojectv2.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String username;

    private final String email;

    public UserResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static UserResponseDto toDto(UserEntity user) {
        return new UserResponseDto(user.getEmail(), user.getPassword());
    }
}
