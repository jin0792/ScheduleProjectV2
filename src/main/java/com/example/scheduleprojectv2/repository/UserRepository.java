package com.example.scheduleprojectv2.repository;


import com.example.scheduleprojectv2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    default UserEntity findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "비밀번호를 조회할 수 없습니다 = " + id
                        )
                );
    }
}
