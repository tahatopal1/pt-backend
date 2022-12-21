package com.project.pt.controller;

import com.project.pt.dto.ErrorDTO;
import com.project.pt.dto.user.PersistableUserDTO;
import com.project.pt.dto.user.ReadableUserDTO;
import com.project.pt.facade.UserFacade;
import com.project.pt.utils.UtilsService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UtilsService utilsService;

    @GetMapping
    public ResponseEntity getUser(){
        try{
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            ReadableUserDTO user = userFacade.getUserByUsername(currentUsername);
            return new ResponseEntity<ReadableUserDTO>(user, HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity updateUser(@RequestBody @Valid PersistableUserDTO userDTO){
        try{
            String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
            if (Strings.isBlank(userDTO.getUsername()) || !userDTO.getUsername().equals(currentUsername)){
                throw new Exception("Invalid username for the request");
            }
            userFacade.saveUser(userDTO);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
