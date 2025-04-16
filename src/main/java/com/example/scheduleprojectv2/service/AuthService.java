package com.example.scheduleprojectv2.service;

import com.example.scheduleprojectv2.config.PasswordEncoder;
import com.example.scheduleprojectv2.dto.user_dto.LoginResponseDto;
import com.example.scheduleprojectv2.entity.UserEntity;
import com.example.scheduleprojectv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public LoginResponseDto login(String email, String password) {
        UserEntity findUser = userRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 조회할 수 없습니다")
        );

        boolean matchedPassword = passwordEncoder.match(password, findUser.getPassword());

        if(!matchedPassword) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        return new LoginResponseDto(findUser.getId(), findUser.getEmail(), findUser.getUsername());
    }
}