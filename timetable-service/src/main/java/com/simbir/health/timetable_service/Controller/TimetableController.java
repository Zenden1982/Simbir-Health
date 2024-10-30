package com.simbir.health.timetable_service.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simbir.health.timetable_service.Class.DTO.AppointmentDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableCreateUpdateDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableReadDTO;
import com.simbir.health.timetable_service.Service.Interface.AppointmentService;
import com.simbir.health.timetable_service.Service.Interface.TimetableService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<TimetableReadDTO> createTimetable(@RequestBody TimetableCreateUpdateDTO dto,
            HttpServletRequest request) {
        return ResponseEntity.ok()
                .body(timetableService.createTimetable(dto, request.getHeader("Authorization")));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TimetableReadDTO> updateTimetable(@PathVariable Long id,
            @RequestBody TimetableCreateUpdateDTO dto, HttpServletRequest request) {
        return ResponseEntity.ok().body(timetableService.updateTimetable(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimetable(@PathVariable Long id, HttpServletRequest request) {
        timetableService.deleteTimetable(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/Doctor/{id}")
    public ResponseEntity<Void> deleteTimetablesByDoctor(@PathVariable Long id, HttpServletRequest request) {
        timetableService.deleteTimetablesByHospital(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/Hospital/{id}")
    public ResponseEntity<Void> deleteTimetablesByHospital(@PathVariable Long id, HttpServletRequest request) {
        timetableService.deleteTimetablesByHospital(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/Hospital/{id}")
    public ResponseEntity<List<TimetableReadDTO>> getTimetablesByHospital(@PathVariable Long id,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {

        return ResponseEntity.ok(timetableService.getTimetableForHospital(id, from, to));
    }

    @GetMapping("/Doctor/{id}")
    public ResponseEntity<List<TimetableReadDTO>> getTimetablesByDoctor(@PathVariable Long id,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(timetableService.getTimetableForDoctor(id, from, to));
    }

    @GetMapping("/Hospital/{id}/Room/{room}")
    public ResponseEntity<List<TimetableReadDTO>> getTimetablesByRoom(@PathVariable Long id,
            @PathVariable String room,
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {

        return ResponseEntity.ok(timetableService.getTimetableForRoom(id, room, from, to));
    }

    @GetMapping("/{id}/Appointments")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsForTimetable(@PathVariable Long id) {
        return ResponseEntity.ok(timetableService.getAppointmentsForTimetable(id));
    }

    @PostMapping("/{id}/Appointments")
    public ResponseEntity<AppointmentDTO> createAppointmentForTimetable(@PathVariable Long id,
            @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time,
            HttpServletRequest request) {
        return ResponseEntity
                .ok(timetableService.createAppointmentForTimetable(id, time, request.getHeader("Authorization")));
    }

    @DeleteMapping("/Appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.delete(id);
        return ResponseEntity.ok().build();
    }
}
