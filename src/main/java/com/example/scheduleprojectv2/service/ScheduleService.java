package com.example.scheduleprojectv2.service;

import com.example.scheduleprojectv2.dto.schedule_dto.ScheduleResponseDto;
import com.example.scheduleprojectv2.entity.ScheduleEntity;
import com.example.scheduleprojectv2.entity.UserEntity;
import com.example.scheduleprojectv2.repository.ScheduleRepository;
import com.example.scheduleprojectv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents, String username) {

        UserEntity findUser = userRepository.findUserByUernameOrElseThrow(username);

        ScheduleEntity schedule = new ScheduleEntity(title, contents, username);
        schedule.setUser(findUser);

        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(),savedSchedule.getTitle(),savedSchedule.getContents());
    }

    public List<ScheduleResponseDto> findAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Optional<ScheduleEntity> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 조회할 수 없습니다 :" + id);
        }

        ScheduleEntity findSchedule = optionalSchedule.get();

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }
}
