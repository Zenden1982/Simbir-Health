package com.simbir.health.timetable_service.Utils;

import org.springframework.stereotype.Component;

import com.simbir.health.timetable_service.Class.Timetable;
import com.simbir.health.timetable_service.Class.DTO.TimetableCreateUpdateDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableReadDTO;

@Component
public class Mapper {

    public TimetableReadDTO mapToReadDTO(Timetable timetable) {
        return new TimetableReadDTO(timetable.getId(), timetable.getHospitalId(), timetable.getDoctorId(),
                timetable.getFrom(), timetable.getTo(), timetable.getRoom());
    }

    public Timetable mapToEntity(TimetableCreateUpdateDTO dto) {
        return new Timetable(null, dto.getHospitalId(), dto.getDoctorId(), dto.getFrom(), dto.getTo(), dto.getRoom(),
                null);
    }

}
