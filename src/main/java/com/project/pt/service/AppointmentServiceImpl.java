package com.project.pt.service;

import com.project.pt.model.Appointment;
import com.project.pt.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(UUID uuid) {
        return appointmentRepository.getReferenceById(uuid);
    }

    @Override
    public List<Appointment> getAllAppointments(int page, int size) {
        return appointmentRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public List<Appointment> getAppointmentsByCustomer(String username, int page, int size) {
        return appointmentRepository.getAppointmentsByCustomer(username, PageRequest.of(page, size));
    }

    @Override
    public List<Appointment> getAppointmentsByTrainer(String username, int page, int size) {
        return appointmentRepository.getAppointmentsByTrainer(username, PageRequest.of(page, size));
    }

    @Override
    public void deleteAppointment(UUID uuid) {
        appointmentRepository.deleteById(uuid);
    }
}
