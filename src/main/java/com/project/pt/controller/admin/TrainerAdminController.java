package com.project.pt.controller.admin;

import com.project.pt.dto.CustomerAssignmentDTO;
import com.project.pt.dto.ErrorDTO;
import com.project.pt.facade.TrainerAssignmentFacade;
import com.project.pt.facade.UserFacade;
import com.project.pt.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/trainer")
public class TrainerAdminController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private TrainerAssignmentFacade trainerAssignmentFacade;

    @Autowired
    private UtilsService utilsService;

    @PostMapping
    public ResponseEntity assignCustomer(@RequestBody @Valid CustomerAssignmentDTO assignmentDTO){
        try{
            trainerAssignmentFacade.assignTrainerToUser(assignmentDTO);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteTrainer(@RequestBody @Valid CustomerAssignmentDTO assignmentDTO){
        try{
            trainerAssignmentFacade.deleteTrainerFromTrainerList(assignmentDTO);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

}
