package com.simbir.health.timetable_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TimetableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableServiceApplication.class, args);
	}

}
