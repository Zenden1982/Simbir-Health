package com.simbir.health.hospital_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simbir.health.hospital_service.Class.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
