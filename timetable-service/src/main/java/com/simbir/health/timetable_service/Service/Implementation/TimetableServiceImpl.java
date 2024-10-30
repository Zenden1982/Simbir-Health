package com.simbir.health.timetable_service.Service.Implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.simbir.health.timetable_service.Class.Appointment;
import com.simbir.health.timetable_service.Class.Timetable;
import com.simbir.health.timetable_service.Class.DTO.AppointmentDTO;
import com.simbir.health.timetable_service.Class.DTO.HospitalDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableCreateUpdateDTO;
import com.simbir.health.timetable_service.Class.DTO.TimetableReadDTO;
import com.simbir.health.timetable_service.Class.DTO.UserDTO;
import com.simbir.health.timetable_service.Client.AccountServiceClient;
import com.simbir.health.timetable_service.Client.HospitalServiceClient;
import com.simbir.health.timetable_service.Repository.AppointmentRepository;
import com.simbir.health.timetable_service.Repository.TimetableRepository;
import com.simbir.health.timetable_service.Service.Interface.TimetableService;
import com.simbir.health.timetable_service.Utils.Mapper;

import jakarta.transaction.Transactional;
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
    @Transactional
    public TimetableReadDTO createTimetable(TimetableCreateUpdateDTO dto, String token) {
        HospitalDTO hospital = hospitalServiceClient.getHospitalById(dto.getHospitalId());
        if (hospital == null) {
            throw new RuntimeException("Hospital not found");
        }

        UserDTO doctor = accountServiceClient.getDoctorById(dto.getDoctorId(), token);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found");
        }
        Timetable timetable = mapper.mapToEntity(dto);
        updateAppointments(timetable);
        return mapper.mapToReadDTO(timetableRepository.save(timetable));
    }

    @Override
    @Transactional
    public TimetableReadDTO updateTimetable(Long id, TimetableCreateUpdateDTO dto) {
        Timetable timetable = timetableRepository.findById(id)
                .map(timetableFound -> {
                    boolean timeChanged = !timetableFound.getFrom().equals(dto.getFrom())
                            || !timetableFound.getTo().equals(dto.getTo());

                    // Обновляем основные поля
                    timetableFound.setHospitalId(dto.getHospitalId());
                    timetableFound.setDoctorId(dto.getDoctorId());
                    timetableFound.setRoom(dto.getRoom());

                    // Если время изменилось, обновляем время и талоны
                    if (timeChanged) {
                        timetableFound.setFrom(dto.getFrom());
                        timetableFound.setTo(dto.getTo());
                        updateAppointments(timetableFound);
                    } else {
                        // Если время не изменилось, просто обновляем основные поля
                        timetableFound.setFrom(dto.getFrom());
                        timetableFound.setTo(dto.getTo());
                    }
                    return timetableRepository.save(timetableFound);
                })
                .orElseThrow(() -> new RuntimeException("Timetable not found"));

        return mapper.mapToReadDTO(timetable);
    }

    private void updateAppointments(Timetable timetable) {
        // Получаем текущие талоны
        List<Appointment> currentAppointments = timetable.getAppointments();

        // Создаем новые талоны на приём
        List<Appointment> newAppointments = new ArrayList<>();
        for (LocalDateTime appointmentTime = timetable.getFrom(); appointmentTime
                .isBefore(timetable.getTo()); appointmentTime = appointmentTime.plusMinutes(30)) {

            Appointment appointment = new Appointment();
            appointment.setTime(appointmentTime);
            appointment.setTimetable(timetable);
            newAppointments.add(appointment);
        }

        // Очищаем текущие талоны и добавляем новые
        currentAppointments.clear();
        currentAppointments.addAll(newAppointments);
    }

    @Override
    @Transactional
    public void deleteTimetable(Long id) {
        timetableRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteTimetablesByHospital(Long id) {
        timetableRepository.deleteAllByHospitalId(id);
    }

    @Override
    @Transactional
    public List<TimetableReadDTO> getTimetableForHospital(Long hospitalId, LocalDateTime from, LocalDateTime to) {
        return timetableRepository.findByHospitalIdAndFromBetween(hospitalId, from, to)
                .stream().map(mapper::mapToReadDTO).toList();
    }

    @Override
    @Transactional
    public List<TimetableReadDTO> getTimetableForDoctor(Long doctorId, LocalDateTime from, LocalDateTime to) {
        return timetableRepository.findByDoctorIdAndFromBetween(doctorId, from, to)
                .stream().map(mapper::mapToReadDTO).toList();
    }

    @Override
    @Transactional
    public List<TimetableReadDTO> getTimetableForRoom(Long hospitalId, String room, LocalDateTime from,
            LocalDateTime to) {
        return timetableRepository.findByHospitalIdAndRoomAndFromBetween(hospitalId, room, from, to)
                .stream().map(mapper::mapToReadDTO).toList();
    }

    @Override
    @Transactional
    public List<AppointmentDTO> getAppointmentsForTimetable(Long id) {
        return appointmentRepository.findByTimetableId(id).stream().map(mapper::mapToDTO).toList();
    }

    @Override
    @Transactional
    public AppointmentDTO createAppointmentForTimetable(Long id, LocalDateTime time, String token) {
        // Timetable timetable = timetableRepository.findById(id)
        // .orElseThrow(() -> new RuntimeException("Timetable not found"));
        UserDTO user = accountServiceClient.getUserInfo(token);
        Appointment appointment = appointmentRepository.findByTimetableIdAndTimeAndIsBooked(id, time, false)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setIsBooked(true);
        appointment.setUserId(user.getId());
        return mapper.mapToDTO(appointmentRepository.save(appointment));
    }

}
