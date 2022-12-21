package com.project.pt.controller;

import com.project.pt.dto.ErrorDTO;
import com.project.pt.dto.user.ReadableUserDTO;
import com.project.pt.facade.TrainerAssignmentFacade;
import com.project.pt.facade.UserFacade;
import com.project.pt.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainer")
public class TrainerController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private TrainerAssignmentFacade trainerAssignmentFacade;

    @GetMapping
    public ResponseEntity getAllTrainers(@RequestParam int page, @RequestParam int size){
        try{
            List<ReadableUserDTO> users = userFacade.findAllTrainers("TRAINER", page, size);
            return new ResponseEntity<List<ReadableUserDTO>>(users, HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{username}")
    public ResponseEntity pickTrainer(@RequestParam String username){
        try{
            trainerAssignmentFacade.assignTrainerToCustomer(username);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity discardTrainer(@RequestParam String username){
        try{
            trainerAssignmentFacade.removeTrainerFromCustomer(username);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

}
