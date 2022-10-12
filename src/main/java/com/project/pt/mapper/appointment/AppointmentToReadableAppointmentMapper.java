package com.project.pt.mapper.appointment;

import com.project.pt.dto.appointment.ReadableAppointmentDTO;
import com.project.pt.mapper.CustomMapper;
import com.project.pt.mapper.user.UserToReadableUserMapper;
import com.project.pt.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentToReadableAppointmentMapper implements CustomMapper<Appointment, ReadableAppointmentDTO> {

    @Autowired
    private UserToReadableUserMapper mapper;

    @Override
    public ReadableAppointmentDTO map(Appointment appointment) {
        return ReadableAppointmentDTO.builder()
                .appointmentDate(appointment.getAppointmentDate())
                .hourSlot(appointment.getHourSlot())
                .user(appointment.getTrainer().getUsername())
                .build();
    }

}
