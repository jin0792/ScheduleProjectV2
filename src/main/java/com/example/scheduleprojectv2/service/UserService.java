package com.example.scheduleprojectv2.service;

import com.example.scheduleprojectv2.dto.user_dto.SignUpResponseDto;
import com.example.scheduleprojectv2.entity.UserEntity;
import com.example.scheduleprojectv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String username, String password, String email) {

        UserEntity user = new UserEntity(username, password, email);

        UserEntity savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }
}
