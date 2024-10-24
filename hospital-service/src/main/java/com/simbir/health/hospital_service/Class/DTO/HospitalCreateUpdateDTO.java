package com.simbir.health.hospital_service.Class.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalCreateUpdateDTO {
    private String name;
    private String address;
    private String contactPhone;
    private List<String> rooms;
}
