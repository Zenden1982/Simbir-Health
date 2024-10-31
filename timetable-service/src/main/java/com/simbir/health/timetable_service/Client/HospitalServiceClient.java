package com.simbir.health.timetable_service.Client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.simbir.health.timetable_service.Class.DTO.HospitalDTO;

@FeignClient(name = "hospital-service", url = "http://localhost:8082/api")
public interface HospitalServiceClient {

    @GetMapping("Hospitals/{id}")
    HospitalDTO getHospitalById(@PathVariable Long id, @RequestHeader("Authorization") String token);

    @GetMapping("Hospitals/{id}/Rooms")
    List<String> getRoomsByHospitalId(@PathVariable Long id, @RequestHeader("Authorization") String token);

}
