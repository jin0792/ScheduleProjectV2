package com.example.scheduleprojectv2.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 임의의 아이디 값으로 만듦
    private Long id;

    @Column(length = 10, nullable = false)
    private String username;
    @Column(length = 20, nullable = false, unique = true)
    private String email;
    @Column(length = 20, nullable = false)
    private String password;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE) // mappedBy 양방향 연관관계에서 부모일 때 사용
    private List<ScheduleEntity> scheduleEntities = new ArrayList<>();

    public UserEntity() {

    }

    public UserEntity(String username, String email, String password ){

        this.username = username;
        this.email = email;
        this.password = password;

    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
