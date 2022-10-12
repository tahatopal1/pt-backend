package com.project.pt.utils;

import com.project.pt.dto.ErrorDTO;
import org.springframework.stereotype.Component;

@Component
public class UtilsService {

    public ErrorDTO generateError(Exception e) {
        ErrorDTO error = ErrorDTO.builder()
                .errorCode("000")
                .errorDesc(e.getMessage())
                .build();
        return error;
    }

}
