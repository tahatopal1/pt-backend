package com.project.pt.facade;

import com.project.pt.dto.appointment.PersistableAppointmentDTO;
import com.project.pt.mapper.appointment.PersistableAppointmentToAppointmentMapper;
import com.project.pt.model.Appointment;
import com.project.pt.model.User;
import com.project.pt.model.constant.Type;
import com.project.pt.repository.AppointmentRepository;
import com.project.pt.service.AppointmentService;
import com.project.pt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AppointmentFacadeImpl implements AppointmentFacade {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PersistableAppointmentToAppointmentMapper appointmentMapper;

    @Override
    public void createAppointment(PersistableAppointmentDTO appointmentDTO) {

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUsername(currentUsername);
        User otherUser = userService.getUserByUsername(appointmentDTO.getUsername());

        Appointment appointment = appointmentMapper.map(appointmentDTO);

        if (Type.CUSTOMER.equals(currentUser.getType())){
            appointment.setCustomer(currentUser);
            appointment.setTrainer(otherUser);

        } else if (Type.TRAINER.equals(currentUser.getType())) {

        }


    }
}
