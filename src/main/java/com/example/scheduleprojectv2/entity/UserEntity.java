package com.example.scheduleprojectv2.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String username;
    @Column(length = 20, nullable = false, unique = true)
    private String email;
    @Column(length = 20, nullable = false)
    private String password;


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
