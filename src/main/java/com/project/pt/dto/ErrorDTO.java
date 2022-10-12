package com.project.pt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {

    private String errorCode;
    private String errorDesc;

}
