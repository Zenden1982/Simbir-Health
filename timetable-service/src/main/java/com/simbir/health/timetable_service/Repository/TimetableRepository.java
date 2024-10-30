package com.simbir.health.timetable_service.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simbir.health.timetable_service.Class.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    void deleteAllByHospitalId(Long id);

    void deleteAllByDoctorId(Long id);

    List<Timetable> findByHospitalIdAndFromBetween(Long hospitalId, LocalDateTime from, LocalDateTime to);

    List<Timetable> findByDoctorIdAndFromBetween(Long doctorId, LocalDateTime from, LocalDateTime to);

    List<Timetable> findByHospitalIdAndRoomAndFromBetween(Long hospitalId, String room, LocalDateTime from,
            LocalDateTime to);

    List<Timetable> findByDoctorId(Long id);
}
