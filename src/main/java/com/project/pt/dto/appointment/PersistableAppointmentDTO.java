package com.project.pt.dto.appointment;

import com.project.pt.dto.BaseEntityDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PersistableAppointmentDTO extends BaseEntityDTO{

    private Date appointmentDate;
    private int hourSlot;
    private String username;

}
