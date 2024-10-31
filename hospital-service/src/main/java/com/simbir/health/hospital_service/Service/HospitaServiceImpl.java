package com.simbir.health.hospital_service.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simbir.health.hospital_service.Class.Hospital;
import com.simbir.health.hospital_service.Class.Room;
import com.simbir.health.hospital_service.Class.DTO.HospitalCreateUpdateDTO;
import com.simbir.health.hospital_service.Class.DTO.HospitalReadDTO;
import com.simbir.health.hospital_service.Client.AccountServiceClient;
import com.simbir.health.hospital_service.Repository.HospitalRepository;
import com.simbir.health.hospital_service.Utils.Mapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HospitaServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    private final AccountServiceClient accountServiceClient;
    private final Mapper mapper;

    @Override
    public HospitalReadDTO createHospital(HospitalCreateUpdateDTO hospitalDTO, String token) {

        if (accountServiceClient.getUserInfo(token).getRoles().contains("ROLE_ADMIN")) {
            Hospital savedHospital = hospitalRepository.save(mapper.mapToEntity(hospitalDTO));
            hospitalRepository.save(savedHospital);
            return mapper.mapToReadDTO(savedHospital);
        } else {
            throw new RuntimeException("Forbidden");
        }
    }

    @Override
    public HospitalReadDTO getHospitalById(Long id, String token) {
        if (accountServiceClient.validate(token.substring(7))) {
            Hospital hospital = hospitalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Hospital not found"));
            return mapper.mapToReadDTO(hospital);
        } else {
            throw new RuntimeException("Forbidden");
        }

    }

    @Override
    public Page<HospitalReadDTO> getAllHospitals(int from, int count, String token) {
        if (accountServiceClient.validate(token.substring(7))) {
            return hospitalRepository.findAll(PageRequest.of(from, count)).map(mapper::mapToReadDTO);

        } else {
            throw new RuntimeException("Forbidden");
        }
    }

    @Override
    public HospitalReadDTO updateHospital(Long id, HospitalCreateUpdateDTO hospitalDTO, String token) {
        if (accountServiceClient.getUserInfo(token).getRoles().contains("ROLE_ADMIN")) {
            Hospital hospital = hospitalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Hospital not found"));

            hospital.setName(hospitalDTO.getName());
            hospital.setAddress(hospitalDTO.getAddress());
            hospital.setContactPhone(hospitalDTO.getContactPhone());

            List<Room> existingRooms = hospital.getRooms();
            List<String> newRoomNames = hospitalDTO.getRooms();

            existingRooms.removeIf(room -> !newRoomNames.contains(room.getName()));

            for (String roomName : newRoomNames) {
                if (existingRooms.stream().noneMatch(room -> room.getName().equals(roomName))) {
                    Room newRoom = new Room();
                    newRoom.setName(roomName);
                    newRoom.setHospital(hospital);
                    existingRooms.add(newRoom);
                }
            }
            Hospital updatedHospital = hospitalRepository.save(hospital);

            return mapper.mapToReadDTO(updatedHospital);
        } else {
            throw new RuntimeException("Forbidden");
        }
    }

    @Override
    public void deleteHospital(Long id, String token) {
        if (accountServiceClient.getUserInfo(token).getRoles().contains("ROLE_ADMIN")) {
            hospitalRepository.deleteById(id);

        } else {
            throw new RuntimeException("Forbidden");
        }
    }

    @Override
    public List<String> getRoomsByHospitalId(Long id, String token) {
        if (accountServiceClient.getUserInfo(token).getRoles().contains("ROLE_ADMIN")) {
            Hospital hospital = hospitalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Hospital not found"));
            return hospital.getRooms().stream().map(Room::getName).toList();
        } else
            throw new RuntimeException("Forbidden");
    }

}
