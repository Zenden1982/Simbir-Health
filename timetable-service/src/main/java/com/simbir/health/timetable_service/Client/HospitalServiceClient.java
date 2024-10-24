package com.simbir.health.timetable_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.simbir.health.timetable_service.Class.HospitalDTO;

@FeignClient(name = "hospital-service")
public interface HospitalServiceClient {

    @GetMapping("api/Hospitals{id}")
    HospitalDTO getHospitalById(@PathVariable Long id);
}
