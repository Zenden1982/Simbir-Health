package com.simbir.health.timetable_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simbir.health.timetable_service.Class.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

}
