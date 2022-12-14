package com.project.pt.service;

import com.project.pt.model.Appointment;
import com.project.pt.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    public Appointment getAppointment(UUID uuid) throws Exception {
        Appointment appointment = appointmentRepository.getReferenceById(uuid);
        if (Objects.isNull(appointment)){
            throw new Exception("There is no such appointment with code: " + uuid.toString());
        }
        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointments(int page, int size, String field, boolean desc) {
        PageRequest pr = desc ? PageRequest.of(page, size, Sort.by(field).descending()) : PageRequest.of(page, size, Sort.by(field));
        return appointmentRepository.findAll(pr).getContent();
    }


    @Override
    public List<Appointment> getAppointmentsByCustomer(String username, int page, int size) {
        return appointmentRepository.getAppointmentsByCustomer(username, PageRequest.of(page, size));
    }

    @Override
    public List<Appointment> getAppointmentByCustomerSorted(String username, int page, int size, String field, boolean desc) {
        PageRequest pr = desc ? PageRequest.of(page, size, Sort.by(field).descending()) : PageRequest.of(page, size, Sort.by(field));
        return appointmentRepository.getAppointmentsByCustomer(username, pr);
    }

    @Override
    public void deleteAppointment(UUID uuid) {
        appointmentRepository.deleteById(uuid);
    }
}
