package com.simbir.health.timetable_service.Service.Interface;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.simbir.health.timetable_service.Class.DTO.AppointmentDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableCreateUpdateDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableReadDTO;

public interface TimetableService {
    TimetableReadDTO createTimetable(TimetableCreateUpdateDTO dto, String token);

    TimetableReadDTO updateTimetable(Long id, TimetableCreateUpdateDTO dto);

    void deleteTimetable(Long id);

    void deleteTimetablesByHospital(Long id);

    TimetableReadDTO getTimetableById(Long id);

    Page<TimetableReadDTO> getTimetableForHospital(Long hospitalId, String from, String to);

    Page<TimetableReadDTO> getTimetableForDoctor(Long doctorId, String from, String to);

    Page<TimetableReadDTO> getTimetableForRoom(Long hospitalId, String room, String from, String to);

    List<AppointmentDTO> getAppointmentsForTimetable(Long id);

    AppointmentDTO createAppointmentForTimetable(Long id, LocalDateTime time);
}
