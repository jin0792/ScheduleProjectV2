package com.example.scheduleprojectv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleProjectV2Application {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleProjectV2Application.class, args);
    }

}
