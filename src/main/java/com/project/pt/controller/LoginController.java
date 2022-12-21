package com.project.pt.controller;

import com.project.pt.dto.ErrorDTO;
import com.project.pt.dto.user.PersistableUserDTO;
import com.project.pt.facade.UserFacade;
import com.project.pt.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/login")
    public ResponseEntity login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid PersistableUserDTO user){
        try {
            userFacade.saveUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
