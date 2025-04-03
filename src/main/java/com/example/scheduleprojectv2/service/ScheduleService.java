package com.example.scheduleprojectv2.service;

import com.example.scheduleprojectv2.dto.schedule_dto.ScheduleResponseDto;
import com.example.scheduleprojectv2.entity.ScheduleEntity;
import com.example.scheduleprojectv2.entity.UserEntity;
import com.example.scheduleprojectv2.repository.ScheduleRepository;
import com.example.scheduleprojectv2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
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
        ScheduleEntity findSchedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 조회할 수 없습니다 :" + id)
        );

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long scheduleId, String title, String contents) {

        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        findSchedule.updateSchedule(title,contents);

        return new ScheduleResponseDto(
                findSchedule.getId(),
                findSchedule.getTitle(),
                findSchedule.getContents()
        );
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {

        ScheduleEntity findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);

        scheduleRepository.delete(findSchedule);

    }
}
