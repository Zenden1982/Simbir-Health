package com.simbir.health.timetable_service.Class.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TimetableCreateUpdateDTO {
    private Long hospitalId;
    private Long doctorId;

    private LocalDateTime from;
    private LocalDateTime to;
    private String room;
}
