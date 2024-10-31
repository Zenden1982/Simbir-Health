package com.simbir.health.hospital_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.simbir.health.hospital_service.Class.DTO.UserDTO;

@FeignClient(name = "account-service", url = "http://localhost:8081/api")
public interface AccountServiceClient {

    @GetMapping("/Accounts/Me")
    UserDTO getUserInfo(@RequestHeader("Authorization") String token);

    @GetMapping("/Authentication/Validate")
    Boolean validate(@RequestParam String token);
}
