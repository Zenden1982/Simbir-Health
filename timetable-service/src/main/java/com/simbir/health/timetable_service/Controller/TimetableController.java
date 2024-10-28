package com.simbir.health.timetable_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simbir.health.timetable_service.Class.DTO.TimetableCreateUpdateDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableReadDTO;
import com.simbir.health.timetable_service.Service.Interface.TimetableService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @PostMapping
    public ResponseEntity<TimetableReadDTO> createTimetable(@RequestBody TimetableCreateUpdateDTO dto,
            HttpServletRequest request) {
        return ResponseEntity.ok()
                .body(timetableService.createTimetable(dto, request.getHeader("Authorization")));

    }
}
