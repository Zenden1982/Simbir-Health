package com.simbir.health.timetable_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.simbir.health.timetable_service.Class.DTO.UserDTO;

@FeignClient(name = "account-service", url = "http://localhost:8081/api")
public interface AccountServiceClient {

    @GetMapping("Doctors/{id}")
    UserDTO getDoctorById(@PathVariable Long id, @RequestHeader("Authorization") String token);

    @GetMapping("Accounts/Me")
    UserDTO getUserInfo(@RequestHeader("Authorization") String token);
}
