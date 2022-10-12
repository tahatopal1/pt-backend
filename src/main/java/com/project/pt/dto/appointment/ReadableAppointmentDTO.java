package com.project.pt.dto.appointment;

import com.project.pt.dto.BaseEntityDTO;
import com.project.pt.dto.user.ReadableUserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ReadableAppointmentDTO extends BaseEntityDTO {

    private Date appointmentDate;
    private int hourSlot;
    private String user;


}
