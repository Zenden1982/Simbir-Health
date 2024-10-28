package com.simbir.health.timetable_service.Class.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

    private Long id;

    private String lastName;

    private String firstName;

    private String username;
}
