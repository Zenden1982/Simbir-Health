package com.simbir.health.hospital_service.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.simbir.health.hospital_service.Class.DTO.HospitalCreateUpdateDTO;
import com.simbir.health.hospital_service.Class.DTO.HospitalReadDTO;

public interface HospitalService {

    HospitalReadDTO createHospital(HospitalCreateUpdateDTO hospitalDTO);

    HospitalReadDTO getHospitalById(Long id);

    Page<HospitalReadDTO> getAllHospitals(int from, int count);

    HospitalReadDTO updateHospital(Long id, HospitalCreateUpdateDTO hospitalDTO);

    List<String> getRoomsByHospitalId(Long id);

    void deleteHospital(Long id);
}
