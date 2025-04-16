package com.example.scheduleprojectv2.controller;

import com.example.scheduleprojectv2.dto.user_dto.LoginRequestDto;
import com.example.scheduleprojectv2.dto.user_dto.LoginResponseDto;
import com.example.scheduleprojectv2.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest httpServletRequest) {

        LoginResponseDto login = authService.login(requestDto.getEmail(), requestDto.getPassword());

        // userId 변수에 저장
        Long id = login.getId();
        // null 값 시 예외 처리
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userId", id);

        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}

