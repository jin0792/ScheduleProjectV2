package com.example.scheduleprojectv2.controller;

import com.example.scheduleprojectv2.dto.user_dto.SignUpRequestDto;
import com.example.scheduleprojectv2.dto.user_dto.SignUpResponseDto;
import com.example.scheduleprojectv2.dto.user_dto.UpdatePasswordRequestDto;
import com.example.scheduleprojectv2.dto.user_dto.UserResponseDto;
import com.example.scheduleprojectv2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // 생성자 주입
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    // ResponseEntity 형태로 응답을 받는다
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        requestDto.getUsername(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );

        return new ResponseEntity<>(signUpResponseDto,HttpStatus.CREATED);
    }


    // 유저 프로필 단 건 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 유저 프로필(비밀번호) 수정
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
