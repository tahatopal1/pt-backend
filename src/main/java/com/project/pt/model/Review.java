package com.project.pt.model;

import com.project.pt.model.constant.Star;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Review extends BaseEntity{

    @Builder.Default
    private Star star = Star.ONE_STAR;

    @Column(columnDefinition = "varchar(2024)")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private User trainer;

}
