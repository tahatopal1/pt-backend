package com.project.pt.controller.admin;

import com.project.pt.dto.ErrorDTO;
import com.project.pt.dto.appointment.PersistableAppointmentDTO;
import com.project.pt.dto.appointment.ReadableAppointmentDTO;
import com.project.pt.facade.AppointmentFacade;
import com.project.pt.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/appointment")
public class AppointmentAdminController {

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private AppointmentFacade appointmentFacade;

    @GetMapping("/{username}")
    public ResponseEntity getAllAppointments(@PathVariable String username,
                                             @RequestParam(required = false) Integer page,
                                             @RequestParam(required = false) Integer size,
                                             @RequestParam(required = false) String field,
                                             @RequestParam(required = false) Boolean desc){
        try {
            List<ReadableAppointmentDTO> appointments = appointmentFacade.getAppointmentsByCustomer(username, page, size, field, desc);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity getAllAppointments(@RequestParam(required = false) Integer page,
                                             @RequestParam(required = false) Integer size,
                                             @RequestParam(required = false) String field,
                                             @RequestParam(required = false) Boolean desc){
        try {
            List<ReadableAppointmentDTO> appointments = appointmentFacade.getAllAppointments(page, size, field, desc);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAppointment(@PathVariable String id){
        try {
            ReadableAppointmentDTO appointment = appointmentFacade.getAppointment(id);
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping
    public ResponseEntity createAppointment(@RequestBody @Valid PersistableAppointmentDTO appointmentDTO){
        try {
            appointmentFacade.createAppointment(appointmentDTO);
            return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity updateAppointment(@RequestBody @Valid PersistableAppointmentDTO appointmentDTO){
        try {
            appointmentFacade.updateAppointment(appointmentDTO);
            return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity cancelAppointment(@PathVariable String id){
        try {
            appointmentFacade.cancelAppointment(id);
            return new ResponseEntity<>("Appointment successfully canceled.", HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }


}
