package com.simbir.health.timetable_service.Class.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentDTO {

    private Long id;

    private LocalDateTime time;
}
