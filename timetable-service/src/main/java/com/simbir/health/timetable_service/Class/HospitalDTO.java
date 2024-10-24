package com.simbir.health.timetable_service.Class;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    private Long id;
    private String name;
    private String address;
    private String contactPhone;
    private List<String> rooms;
}