package com.example.scheduleprojectv2.repository;


import com.example.scheduleprojectv2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
