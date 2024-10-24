package com.simbir.health.hospital_service.Service;

import org.springframework.data.domain.Page;

import com.simbir.health.hospital_service.Class.DTO.HospitalCreateUpdateDTO;
import com.simbir.health.hospital_service.Class.DTO.HospitalReadDTO;

public interface HospitalService {

    HospitalReadDTO createHospital(HospitalCreateUpdateDTO hospitalDTO);

    HospitalReadDTO getHospitalById(Long id);

    Page<HospitalReadDTO> getAllHospitals(int from, int count);

    HospitalReadDTO updateHospital(Long id, HospitalCreateUpdateDTO hospitalDTO);

    void deleteHospital(Long id);
}
