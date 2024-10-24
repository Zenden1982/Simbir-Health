package com.simbir.health.hospital_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simbir.health.hospital_service.Class.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
