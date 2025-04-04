package com.example.scheduleprojectv2.controller;

import com.example.scheduleprojectv2.dto.user_dto.LoginRequestDto;
import com.example.scheduleprojectv2.dto.user_dto.SignUpRequestDto;
import com.example.scheduleprojectv2.dto.user_dto.SignUpResponseDto;
import com.example.scheduleprojectv2.dto.user_dto.UpdatePasswordRequestDto;
import com.example.scheduleprojectv2.dto.user_dto.UserResponseDto;
import com.example.scheduleprojectv2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/id")
    public ResponseEntity<UserResponseDto> login(
            @RequestBody LoginRequestDto requestDto,
            HttpServletRequest httpServletRequest
    ) {

        UserResponseDto login = userService.login(requestDto.getEmail(), requestDto.getPassword());

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("LOGIN_USER", login);

        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
