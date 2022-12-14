package com.project.pt.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerAssignmentDTO {

    @NotNull
    private String trainer;

    @NotNull
    private String customer;

}
