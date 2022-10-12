package com.project.pt.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "APPOINTMENT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Appointment extends BaseEntity{

    @Temporal(TemporalType.DATE)
    private Date appointmentDate;

    private int hourSlot;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainer;


}
