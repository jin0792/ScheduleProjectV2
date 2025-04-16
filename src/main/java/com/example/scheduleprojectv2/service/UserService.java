package com.example.scheduleprojectv2.service;

import com.example.scheduleprojectv2.config.PasswordEncoder;
import com.example.scheduleprojectv2.dto.user_dto.SignUpResponseDto;
import com.example.scheduleprojectv2.dto.user_dto.UserResponseDto;
import com.example.scheduleprojectv2.entity.UserEntity;
import com.example.scheduleprojectv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    // repository에 접근
    private final UserRepository userRepository;

    // encoder에 접근
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public SignUpResponseDto signUp(String username, String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        UserEntity user = new UserEntity(username, email, encodedPassword);

        UserEntity savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponseDto findById(Long id) {

//        Optional<UserEntity> optionalUser = userRepository.findById(id);
//
//        if (optionalUser.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 조회할 수 없습니다 :" + id);
//        }
//
//        UserEntity findUser = optionalUser.get();
//
//        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());

        UserEntity findUser = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 조회할 수 없습니다. :" + id)
        );

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    @Transactional  // 변경감지는 동일한 트랜잭션 안에서 작업을 해야함
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        UserEntity findUser = userRepository.findByIdOrElseThrow(id);

        if (!findUser.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findUser.updatePassword(newPassword);
    }

    public void deleteUser(Long id) {
        UserEntity findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);
    }
}
