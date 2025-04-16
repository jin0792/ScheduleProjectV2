package com.example.scheduleprojectv2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    // 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 임의의 아이디 값으로 만듦
    private Long id;

    // 이름
    @Column(length = 10, nullable = false)
    private String username;

    // 이메일
    @Column(length = 20, nullable = false, unique = true)
    private String email;

    // 비밀번호
    @Column(length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, orphanRemoval = true) // mappedBy 양방향 연관관계에서 주인일 때 사용
    private List<ScheduleEntity> scheduleEntities = new ArrayList<>();


    public UserEntity(String username, String email, String password ){

        this.username = username;
        this.email = email;
        this.password = password;

    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
