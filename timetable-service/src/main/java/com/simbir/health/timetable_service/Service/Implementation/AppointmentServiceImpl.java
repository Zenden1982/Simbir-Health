package com.simbir.health.timetable_service.Service.Implementation;

import org.springframework.stereotype.Service;

import com.simbir.health.timetable_service.Repository.AppointmentRepository;
import com.simbir.health.timetable_service.Service.Interface.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentRepository appointmentRepository;

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
