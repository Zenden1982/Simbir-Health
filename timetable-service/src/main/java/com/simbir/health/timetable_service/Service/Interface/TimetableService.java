package com.simbir.health.timetable_service.Service.Interface;

import java.time.LocalDateTime;
import java.util.List;

import com.simbir.health.timetable_service.Class.DTO.AppointmentDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableCreateUpdateDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableReadDTO;

public interface TimetableService {
    TimetableReadDTO createTimetable(TimetableCreateUpdateDTO dto, String token);

    TimetableReadDTO updateTimetable(Long id, TimetableCreateUpdateDTO dto, String token);

    void deleteTimetable(Long id, String token);

    void deleteTimetablesByHospital(Long id, String token);

    List<TimetableReadDTO> getTimetableForHospital(Long hospitalId, LocalDateTime from, LocalDateTime to, String token);

    List<TimetableReadDTO> getTimetableForDoctor(Long doctorId, LocalDateTime from, LocalDateTime to, String token);

    List<TimetableReadDTO> getTimetableForRoom(Long hospitalId, String room, LocalDateTime from, LocalDateTime to,
            String token);

    List<AppointmentDTO> getAppointmentsForTimetable(Long id, String token);

    AppointmentDTO createAppointmentForTimetable(Long id, LocalDateTime time, String token);

    void cancelBookedAppointment(Long id, String token);
}
