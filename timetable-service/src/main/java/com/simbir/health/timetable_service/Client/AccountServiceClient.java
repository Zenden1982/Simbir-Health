package com.simbir.health.timetable_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.simbir.health.timetable_service.Class.DoctorDTO;

@FeignClient(name = "account-service")
public interface AccountServiceClient {

    @GetMapping("api/Doctors/{id}")
    DoctorDTO getDoctorById(@PathVariable Long id);
}
