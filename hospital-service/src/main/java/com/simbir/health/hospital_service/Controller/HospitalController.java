package com.simbir.health.hospital_service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.simbir.health.hospital_service.Class.DTO.HospitalCreateUpdateDTO;
import com.simbir.health.hospital_service.Class.DTO.HospitalReadDTO;
import com.simbir.health.hospital_service.Service.HospitalService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<HospitalReadDTO> createHospital(@RequestBody HospitalCreateUpdateDTO hospitalDTO,
            HttpServletRequest request) {
        HospitalReadDTO createdHospital = hospitalService.createHospital(hospitalDTO,
                request.getHeader("Authorization"));
        return new ResponseEntity<>(createdHospital, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalReadDTO> getHospitalById(@PathVariable Long id, HttpServletRequest request) {
        HospitalReadDTO hospital = hospitalService.getHospitalById(id, request.getHeader("Authorization"));
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @GetMapping("/{id}/Rooms")
    public ResponseEntity<List<String>> getRoomsByHospitalId(@PathVariable Long id, HttpServletRequest request) {

        return ResponseEntity.ok(hospitalService.getRoomsByHospitalId(id, request.getHeader("Authorization")));
    }

    @GetMapping
    public ResponseEntity<Page<HospitalReadDTO>> getAllHospitals(@RequestParam int from, @RequestParam int count,
            HttpServletRequest request) {
        Page<HospitalReadDTO> hospitals = hospitalService.getAllHospitals(from, count,
                request.getHeader("Authorization"));
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalReadDTO> updateHospital(@PathVariable Long id,
            @RequestBody HospitalCreateUpdateDTO hospitalDTO, HttpServletRequest request) {
        HospitalReadDTO updatedHospital = hospitalService.updateHospital(id, hospitalDTO,
                request.getHeader("Authorization"));
        return new ResponseEntity<>(updatedHospital, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id, HttpServletRequest request) {
        hospitalService.deleteHospital(id, request.getHeader("Authorization"));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
