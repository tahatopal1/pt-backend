package com.project.pt.service;

import com.project.pt.model.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    void saveAppointment(Appointment appointment);

    Appointment getAppointment(UUID uuid);

    List<Appointment> getAllAppointments(int page, int size);

    List<Appointment> getAppointmentsByCustomer(String username, int page, int size);

    List<Appointment> getAppointmentsByTrainer(String username, int page, int size);

    void deleteAppointment(UUID uuid);

}
