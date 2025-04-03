package com.example.scheduleprojectv2.service;

import com.example.scheduleprojectv2.dto.schedule_dto.ScheduleResponseDto;
import com.example.scheduleprojectv2.entity.ScheduleEntity;
import com.example.scheduleprojectv2.entity.UserEntity;
import com.example.scheduleprojectv2.repository.ScheduleRepository;
import com.example.scheduleprojectv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents, String username) {

        UserEntity findUser = userRepository.findUserByUernameOrElseThrow(username);

        ScheduleEntity schedule = new ScheduleEntity(title, contents);
        schedule.setUser(findUser);

        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(),savedSchedule.getTitle(),savedSchedule.getContents());
    }
}
