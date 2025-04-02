package com.example.scheduleprojectv2.controller;

import com.example.scheduleprojectv2.dto.user_dto.SignUpRequestDto;
import com.example.scheduleprojectv2.dto.user_dto.SignUpResponseDto;
import com.example.scheduleprojectv2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // 생성자 주입
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    // ResponseEntity 형태로 응답을 받는다
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        requestDto.getUsername(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );

        return new ResponseEntity<>(signUpResponseDto,HttpStatus.CREATED);

    }

}
