package com.project.pt.dto.appointment;

import com.project.pt.dto.BaseEntityDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class PersistableAppointmentDTO extends BaseEntityDTO{

    @NotNull
    private Date appointmentDate;

    @NotNull
    private String customer;

    @NotNull
    private String trainer;

}
