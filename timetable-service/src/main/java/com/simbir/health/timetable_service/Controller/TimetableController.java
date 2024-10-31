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
import com.simbir.health.timetable_service.Service.Interface.TimetableService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @Operation(summary = "Создать новое расписание")
    @PostMapping
    public ResponseEntity<TimetableReadDTO> createTimetable(
            @RequestBody TimetableCreateUpdateDTO dto,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        return ResponseEntity.ok()
                .body(timetableService.createTimetable(dto, request.getHeader("Authorization")));
    }

    @Operation(summary = "Обновить расписание по ID")
    @PutMapping("/{id}")
    public ResponseEntity<TimetableReadDTO> updateTimetable(
            @PathVariable Long id,
            @RequestBody TimetableCreateUpdateDTO dto,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        return ResponseEntity.ok().body(timetableService.updateTimetable(id, dto, request.getHeader("Authorization")));
    }

    @Operation(summary = "Удалить расписание по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimetable(
            @PathVariable Long id,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        timetableService.deleteTimetable(id, request.getHeader("Authorization"));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить все расписания для врача по ID")
    @DeleteMapping("/Doctor/{id}")
    public ResponseEntity<Void> deleteTimetablesByDoctor(
            @PathVariable Long id,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        timetableService.deleteTimetablesByHospital(id, request.getHeader("Authorization"));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить все расписания для больницы в заданном временном диапазоне")
    @GetMapping("/Hospital/{id}")
    public ResponseEntity<List<TimetableReadDTO>> getTimetablesByHospital(
            @PathVariable Long id,
            @Parameter(description = "Дата начала", required = true) @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @Parameter(description = "Дата окончания", required = true) @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        return ResponseEntity
                .ok(timetableService.getTimetableForHospital(id, from, to, request.getHeader("Authorization")));
    }

    @Operation(summary = "Удалить все расписания для больницы по ID")
    @DeleteMapping("/Hospital/{id}")
    public ResponseEntity<Void> deleteTimetablesByHospital(
            @PathVariable Long id,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        timetableService.deleteTimetablesByHospital(id, request.getHeader("Authorization"));
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить все расписания для врача в заданном временном диапазоне")
    @GetMapping("/Doctor/{id}")
    public ResponseEntity<List<TimetableReadDTO>> getTimetablesByDoctor(
            @PathVariable Long id,
            @Parameter(description = "Дата начала", required = true) @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @Parameter(description = "Дата окончания", required = true) @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        return ResponseEntity
                .ok(timetableService.getTimetableForDoctor(id, from, to, request.getHeader("Authorization")));
    }

    @Operation(summary = "Получить все расписания для определенной комнаты в больнице за заданный временной диапазон")
    @GetMapping("/Hospital/{id}/Room/{room}")
    public ResponseEntity<List<TimetableReadDTO>> getTimetablesByRoom(
            @PathVariable Long id,
            @PathVariable String room,
            @Parameter(description = "Дата начала", required = true) @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @Parameter(description = "Дата окончания", required = true) @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        return ResponseEntity
                .ok(timetableService.getTimetableForRoom(id, room, from, to, request.getHeader("Authorization")));
    }

    @Operation(summary = "Получить список назначений для конкретного расписания")
    @GetMapping("/{id}/Appointments")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsForTimetable(
            @PathVariable Long id,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        return ResponseEntity.ok(timetableService.getAppointmentsForTimetable(id, request.getHeader("Authorization")));
    }

    @Operation(summary = "Создать назначение для конкретного расписания")
    @PostMapping("/{id}/Appointments")
    public ResponseEntity<AppointmentDTO> createAppointmentForTimetable(
            @PathVariable Long id,
            @Parameter(description = "Время назначения", required = true) @RequestParam("time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime time,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        return ResponseEntity
                .ok(timetableService.createAppointmentForTimetable(id, time, request.getHeader("Authorization")));
    }

    @Operation(summary = "Удалить назначение по ID")
    @DeleteMapping("/Appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable Long id,
            @Parameter(description = "Токен авторизации") HttpServletRequest request) {
        timetableService.cancelBookedAppointment(id, request.getHeader("Authorization"));
        return ResponseEntity.ok().build();
    }
}