package com.project.pt.facade;

import com.project.pt.dto.appointment.PersistableAppointmentDTO;
import com.project.pt.dto.appointment.ReadableAppointmentDTO;
import com.project.pt.mapper.appointment.AppointmentToReadableAppointmentMapper;
import com.project.pt.mapper.appointment.PersistableAppointmentToAppointmentMapper;
import com.project.pt.model.Appointment;
import com.project.pt.service.AppointmentService;
import com.project.pt.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AppointmentFacadeImpl implements AppointmentFacade {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PersistableAppointmentToAppointmentMapper appointmentMapper;

    @Autowired
    private AppointmentToReadableAppointmentMapper readableAppointmentMapper;

    @Override
    public void createAppointment(PersistableAppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.map(appointmentDTO);
        appointmentService.saveAppointment(appointment);
    }

    @Override
    public void updateAppointment(PersistableAppointmentDTO appointmentDTO) throws Exception {
        if (Strings.isBlank(appointmentDTO.getId())){
            throw new Exception("Appointment code is null!");
        }
        this.getAppointment(appointmentDTO.getId());
        Appointment appointment = appointmentMapper.map(appointmentDTO);
        appointmentService.saveAppointment(appointment);
    }

    @Override
    public List<ReadableAppointmentDTO> getAllAppointments(Integer page, Integer size, String field, Boolean desc) {
        return appointmentService.getAllAppointments(page, size, field, desc)
                .stream()
                .map(readableAppointmentMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadableAppointmentDTO> getAppointmentsByCustomer(String username, Integer page, Integer size, String field, Boolean desc) {
        List<Appointment> appointments = new ArrayList<>();
        if (Strings.isNotBlank(field) && Objects.nonNull(desc)){
            appointments = appointmentService.getAppointmentByCustomerSorted(username, page, size, field, desc);
        } else {
            appointments = appointmentService.getAppointmentsByCustomer(username, page, size);
        }
        return appointments.stream().map(readableAppointmentMapper::map).collect(Collectors.toList());
    }

    @Override
    public ReadableAppointmentDTO getAppointment(String id) throws Exception {
        Appointment appointment = appointmentService.getAppointment(UUID.fromString(id));

        return readableAppointmentMapper.map(appointment);
    }

    @Override
    public void cancelAppointment(String id) throws Exception {
        Appointment appointment = appointmentService.getAppointment(UUID.fromString(id));
        appointmentService.deleteAppointment(appointment.getId());
    }


}
