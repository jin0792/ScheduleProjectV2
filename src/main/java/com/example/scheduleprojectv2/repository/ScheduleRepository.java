package com.example.scheduleprojectv2.repository;

import com.example.scheduleprojectv2.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
}
