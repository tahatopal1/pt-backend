package com.project.pt.facade;

import com.project.pt.dto.appointment.PersistableAppointmentDTO;
import com.project.pt.dto.appointment.ReadableAppointmentDTO;

import java.util.List;

public interface AppointmentFacade {

    void createAppointment(PersistableAppointmentDTO appointmentDTO);

    void updateAppointment(PersistableAppointmentDTO appointmentDTO) throws Exception;

    List<ReadableAppointmentDTO> getAllAppointments(Integer page, Integer size, String field, Boolean desc);

    List<ReadableAppointmentDTO> getAppointmentsByCustomer(String username, Integer page, Integer size, String field, Boolean desc);

    ReadableAppointmentDTO getAppointment(String id) throws Exception;

    void cancelAppointment(String id) throws Exception;


}
