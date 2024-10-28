package com.simbir.health.timetable_service.Service.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.simbir.health.timetable_service.Class.Appointment;
import com.simbir.health.timetable_service.Class.Timetable;
import com.simbir.health.timetable_service.Class.DTO.AppointmentDTO;
import com.simbir.health.timetable_service.Class.DTO.DoctorDTO;
import com.simbir.health.timetable_service.Class.DTO.HospitalDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableCreateUpdateDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableReadDTO;
import com.simbir.health.timetable_service.Client.AccountServiceClient;
import com.simbir.health.timetable_service.Client.HospitalServiceClient;
import com.simbir.health.timetable_service.Repository.AppointmentRepository;
import com.simbir.health.timetable_service.Repository.TimetableRepository;
import com.simbir.health.timetable_service.Service.Interface.TimetableService;
import com.simbir.health.timetable_service.Utils.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final AppointmentRepository appointmentRepository;
    private final HospitalServiceClient hospitalServiceClient;
    private final AccountServiceClient accountServiceClient;
    private final Mapper mapper;

    @Override
    public TimetableReadDTO createTimetable(TimetableCreateUpdateDTO dto, String token) {
        HospitalDTO hospital = hospitalServiceClient.getHospitalById(dto.getHospitalId());
        if (hospital == null) {
            throw new RuntimeException("Hospital not found");
        }

        DoctorDTO doctor = accountServiceClient.getDoctorById(dto.getDoctorId(), token);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }
        Timetable timetable = mapper.mapToEntity(dto);

        List<Appointment> appointments = new ArrayList<>();

        for (LocalDateTime from = dto.getFrom(); from.isBefore(dto.getTo()); from = from.plusMinutes(30)) {
            appointments.add(new Appointment(null, timetable, from));
        }

        timetable.setAppointments(appointments);

        return mapper.mapToReadDTO(timetableRepository.save(timetable));
    }

    @Override
    public TimetableReadDTO updateTimetable(Long id, TimetableCreateUpdateDTO dto) {
        Timetable timetable = timetableRepository.findById(id).map(timetableFound -> {
            timetableFound.setHospitalId(dto.getHospitalId());
            timetableFound.setDoctorId(dto.getDoctorId());
            timetableFound.setFrom(dto.getFrom());
            timetableFound.setTo(dto.getTo());
            timetableFound.setRoom(dto.getRoom());
            return timetableRepository.save(timetableFound);
        }).orElseThrow(() -> new RuntimeException("Timetable not found"));
        return mapper.mapToReadDTO(timetable);
    }

    @Override
    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }

    @Override
    public void deleteTimetablesByHospital(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTimetablesByHospital'");
    }

    @Override
    public TimetableReadDTO getTimetableById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimetableById'");
    }

    @Override
    public Page<TimetableReadDTO> getTimetableForHospital(Long hospitalId, String from, String to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimetableForHospital'");
    }

    @Override
    public Page<TimetableReadDTO> getTimetableForDoctor(Long doctorId, String from, String to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimetableForDoctor'");
    }

    @Override
    public Page<TimetableReadDTO> getTimetableForRoom(Long hospitalId, String room, String from, String to) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTimetableForRoom'");
    }

    @Override
    public List<AppointmentDTO> getAppointmentsForTimetable(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAppointmentsForTimetable'");
    }

    @Override
    public AppointmentDTO createAppointmentForTimetable(Long id, LocalDateTime time) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAppointmentForTimetable'");
    }

}
