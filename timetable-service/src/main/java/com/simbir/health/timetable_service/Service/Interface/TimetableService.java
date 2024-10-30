package com.simbir.health.timetable_service.Service.Interface;

import java.time.LocalDateTime;
import java.util.List;

import com.simbir.health.timetable_service.Class.DTO.AppointmentDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableCreateUpdateDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableReadDTO;

public interface TimetableService {
    TimetableReadDTO createTimetable(TimetableCreateUpdateDTO dto, String token);

    TimetableReadDTO updateTimetable(Long id, TimetableCreateUpdateDTO dto);

    void deleteTimetable(Long id);

    void deleteTimetablesByHospital(Long id);

    List<TimetableReadDTO> getTimetableForHospital(Long hospitalId, LocalDateTime from, LocalDateTime to);

    List<TimetableReadDTO> getTimetableForDoctor(Long doctorId, LocalDateTime from, LocalDateTime to);

    List<TimetableReadDTO> getTimetableForRoom(Long hospitalId, String room, LocalDateTime from, LocalDateTime to);

    List<AppointmentDTO> getAppointmentsForTimetable(Long id);

    AppointmentDTO createAppointmentForTimetable(Long id, LocalDateTime time, String token);
}
