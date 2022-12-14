package com.project.pt.handler;

import com.project.pt.constants.ApplicationConstants;
import com.project.pt.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationExceptions(MethodArgumentNotValidException ex) {

        FieldError error;

        if (ex.getBindingResult().getObjectName().equals("persistableUserDTO")){
            error = ex.getBindingResult().getFieldErrors().stream().reduce((fieldError, fieldError2) -> {
                if (ApplicationConstants.userValidationPriority.get(fieldError.getField()) <
                        ApplicationConstants.userValidationPriority.get(fieldError2.getField())) {
                    return fieldError;
                } else {
                    return fieldError2;
                }
            }).get();
        } else {
            error = ex.getBindingResult().getFieldErrors().get(0);
        }

        ErrorDTO errorEntity = ErrorDTO.builder()
                .errorCode("000")
                .errorDesc(error.getDefaultMessage())
                .build();

        if (error.getField().contains("password")){
            errorEntity.setErrorDesc(error.getDefaultMessage().replace(",","\n"));
        }

        return new ResponseEntity<ErrorDTO>(errorEntity, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerExceptions(NullPointerException ex) {
        ErrorDTO errorEntity = ErrorDTO.builder()
                .errorCode("000")
                .errorDesc(ex.getMessage())
                .build();
        return new ResponseEntity<ErrorDTO>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}
