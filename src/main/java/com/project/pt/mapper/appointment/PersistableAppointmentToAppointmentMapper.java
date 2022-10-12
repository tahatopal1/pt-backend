package com.project.pt.mapper.appointment;

import com.project.pt.dto.appointment.PersistableAppointmentDTO;
import com.project.pt.mapper.CustomMapper;
import com.project.pt.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class PersistableAppointmentToAppointmentMapper implements CustomMapper<PersistableAppointmentDTO, Appointment> {

    @Override
    public Appointment map(PersistableAppointmentDTO persistableAppointmentDTO) {
        return Appointment.builder()
                .appointmentDate(persistableAppointmentDTO.getAppointmentDate())
                .hourSlot(persistableAppointmentDTO.getHourSlot())
                .build();
    }
}
