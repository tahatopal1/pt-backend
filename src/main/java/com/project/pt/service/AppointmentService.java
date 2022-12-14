package com.project.pt.service;

import com.project.pt.model.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    void saveAppointment(Appointment appointment);

    Appointment getAppointment(UUID uuid) throws Exception;

    List<Appointment> getAllAppointments(int page, int size, String field, boolean desc);

    List<Appointment> getAppointmentsByCustomer(String username, int page, int size);

    List<Appointment> getAppointmentByCustomerSorted(String username, int page, int size, String field, boolean desc);

    void deleteAppointment(UUID uuid);

}
