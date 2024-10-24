package com.simbir.health.account_service.Service.Interface;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.simbir.health.account_service.Class.DTO.UserReadDTO;

@Service
public interface DoctorService {

    Page<UserReadDTO> getAllDoctors(String nameFilter, Integer page, Integer size);

    UserReadDTO getDoctorById(Long id);
}
