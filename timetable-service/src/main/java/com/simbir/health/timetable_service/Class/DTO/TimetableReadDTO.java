package com.simbir.health.timetable_service.Class.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableReadDTO {
    private Long id;

    private Long hospitalId;
    private Long doctorId;

    private LocalDateTime from;
    private LocalDateTime to;
    private String room;
}
