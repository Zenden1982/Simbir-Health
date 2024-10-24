package com.simbir.health.hospital_service.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.simbir.health.hospital_service.Class.Hospital;
import com.simbir.health.hospital_service.Class.Room;
import com.simbir.health.hospital_service.Class.DTO.HospitalCreateUpdateDTO;
import com.simbir.health.hospital_service.Class.DTO.HospitalReadDTO;
import com.simbir.health.hospital_service.Repository.HospitalRepository;
import com.simbir.health.hospital_service.Utils.Mapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HospitaServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    private final Mapper mapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public HospitalReadDTO createHospital(HospitalCreateUpdateDTO hospitalDTO) {

        Hospital savedHospital = hospitalRepository.save(mapper.mapToEntity(hospitalDTO));
        hospitalRepository.save(savedHospital);

        kafkaTemplate.send("auth-request", UUID.randomUUID().toString());
        return mapper.mapToReadDTO(savedHospital);
    }

    @Override
    public HospitalReadDTO getHospitalById(Long id) {
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));
        return mapper.mapToReadDTO(hospital);
    }

    @Override
    public Page<HospitalReadDTO> getAllHospitals(int from, int count) {
        return hospitalRepository.findAll(PageRequest.of(from, count)).map(mapper::mapToReadDTO);
    }

    @Override
    public HospitalReadDTO updateHospital(Long id, HospitalCreateUpdateDTO hospitalDTO) {
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
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }

}
