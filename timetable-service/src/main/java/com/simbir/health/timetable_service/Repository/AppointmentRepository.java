package com.simbir.health.timetable_service.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simbir.health.timetable_service.Class.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByTimetableId(Long id);

    void deleteAllByTimetableId(Long id);

    Optional<Appointment> findByTimetableIdAndTimeAndIsBooked(Long id, LocalDateTime time, Boolean isBooked);
}
