package com.example.scheduleprojectv2.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor
public class ScheduleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 임의의 아이디 값으로 만듬
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 100, nullable = false)
    private String contents;
    @Column(length = 10, nullable = false)
    private String username;

    // 연관관계 설정하기
    // 한명의 유저는 여러개의 일정을 작성할 수 있다
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity users;

    public ScheduleEntity(String title, String contents, String username) {
        this.title = title;
        this.contents = contents;
        this.username = username;
    }

    public void setUser(UserEntity userEntity) {
        this.users = userEntity;
    }

    public void updateSchedule(String title, String contents) {
        this.title = title;
        this.contents = contents;

    }
}
