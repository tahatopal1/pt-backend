package com.project.pt.mapper.appointment;

import com.project.pt.dto.appointment.PersistableAppointmentDTO;
import com.project.pt.mapper.CustomMapper;
import com.project.pt.model.Appointment;
import com.project.pt.model.User;
import com.project.pt.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersistableAppointmentToAppointmentMapper implements CustomMapper<PersistableAppointmentDTO, Appointment> {

    @Autowired
    private UserService userService;

    @Override
    public Appointment map(PersistableAppointmentDTO persistableAppointmentDTO) {

        User customer = userService.getUserByUsername(persistableAppointmentDTO.getCustomer());
        User trainer = userService.getUserByUsername(persistableAppointmentDTO.getTrainer());

        return Appointment.builder()
                .id(UUID.fromString(persistableAppointmentDTO.getId()))
                .appointmentDate(persistableAppointmentDTO.getAppointmentDate())
                .customer(customer)
                .trainer(trainer)
                .build();
    }
}
