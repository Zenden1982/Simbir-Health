package com.simbir.health.hospital_service.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.simbir.health.hospital_service.Class.DTO.HospitalCreateUpdateDTO;
import com.simbir.health.hospital_service.Class.DTO.HospitalReadDTO;

public interface HospitalService {

    HospitalReadDTO createHospital(HospitalCreateUpdateDTO hospitalDTO, String token);

    HospitalReadDTO getHospitalById(Long id, String token);

    Page<HospitalReadDTO> getAllHospitals(int from, int count, String token);

    HospitalReadDTO updateHospital(Long id, HospitalCreateUpdateDTO hospitalDTO, String token);

    List<String> getRoomsByHospitalId(Long id, String token);

    void deleteHospital(Long id, String token);
}
