package com.example.scheduleprojectv2.entity;


import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "schedules")
public class ScheduleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 임의의 아이디 값으로 만듬
    private Long id;
    @Column(length = 10, nullable = false)
    private String username;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity users;
}
