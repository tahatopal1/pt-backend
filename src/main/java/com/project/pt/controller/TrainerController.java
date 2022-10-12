package com.project.pt.controller;

import com.project.pt.dto.ErrorDTO;
import com.project.pt.facade.UserFacade;
import com.project.pt.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UtilsService utilsService;

    @PostMapping("/{username}")
    public ResponseEntity addTrainerToUser(@PathVariable String username){
        try{
            userFacade.assignTrainerToUser(username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deleteTrainer(@PathVariable String username){
        try{
            userFacade.deleteTrainerFromTrainerList(username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

}
