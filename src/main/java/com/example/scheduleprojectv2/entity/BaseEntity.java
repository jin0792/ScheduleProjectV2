package com.example.scheduleprojectv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 생성일, 수정일 자동화 하는 곳
// BaseEntity는 추상클래스 abstract 사용하는게 좋다.
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
//    @Temporal(TemporalType.TIMESTAMP) 생략가능
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}