package com.project.pt.facade;

import com.project.pt.dto.appointment.PersistableAppointmentDTO;

public interface AppointmentFacade {

    void createAppointment(PersistableAppointmentDTO appointmentDTO);

}
