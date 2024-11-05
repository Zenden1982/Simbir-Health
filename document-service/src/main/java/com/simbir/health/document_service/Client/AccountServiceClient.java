package com.simbir.health.document_service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.simbir.health.document_service.Class.UserDTO;

@Component
@FeignClient(name = "account-service", url = "http://localhost:8081/api")
public interface AccountServiceClient {

    // TODO Добавить передачу токена и валидация для получения докторов и юзеров
    @GetMapping("Doctors/{id}")
    UserDTO getDoctorById(@PathVariable Long id, @RequestHeader("Authorization") String token);

    @GetMapping("Accounts/Me")
    UserDTO getUserInfo(@RequestHeader("Authorization") String token);

    @GetMapping("/Authentication/Validate")
    Boolean validate(@RequestParam String token);
}
