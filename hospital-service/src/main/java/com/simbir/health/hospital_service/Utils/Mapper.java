package com.simbir.health.hospital_service.Utils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.simbir.health.hospital_service.Class.Hospital;
import com.simbir.health.hospital_service.Class.Room;
import com.simbir.health.hospital_service.Class.DTO.HospitalCreateUpdateDTO;
import com.simbir.health.hospital_service.Class.DTO.HospitalReadDTO;

@Component
public class Mapper {

    public HospitalReadDTO mapToReadDTO(Hospital hospital) {
        return new HospitalReadDTO(hospital.getId(), hospital.getName(),
                hospital.getAddress(), hospital.getContactPhone(),
                hospital.getRooms().stream().map(room -> room.getName()).collect(Collectors.toList()));
    }

    public Hospital mapToEntity(HospitalCreateUpdateDTO dto) {
        Hospital hospital = new Hospital(null, dto.getName(), dto.getAddress(), dto.getContactPhone(),
                new ArrayList<>());
        dto.getRooms().forEach(name -> hospital.getRooms().add(new Room(null, name, hospital)));
        return hospital;
    }
}
