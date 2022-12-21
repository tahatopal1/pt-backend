package com.project.pt.controller.admin;

import com.project.pt.dto.ErrorDTO;
import com.project.pt.dto.user.PersistableUserDTO;
import com.project.pt.dto.user.ReadableUserDTO;
import com.project.pt.facade.UserFacade;
import com.project.pt.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
public class UserAdminController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UtilsService utilsService;

    @PostMapping
    public ResponseEntity saveUser(@RequestBody @Valid PersistableUserDTO user){
        try {
            userFacade.saveUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getUser(@PathVariable String id){
        try{
            ReadableUserDTO user = userFacade.getUser(id);
            return new ResponseEntity<ReadableUserDTO>(user, HttpStatus.OK);
        } catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity getAllUsers(@RequestParam Integer page, @RequestParam Integer size){
        try{
            List<ReadableUserDTO> users = userFacade.findAllNonAdmins(page, size);
            return new ResponseEntity<List<ReadableUserDTO>>(users, HttpStatus.OK);
        }catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody @Valid PersistableUserDTO user){
        try{
            userFacade.updateUser(user);
            return new ResponseEntity<PersistableUserDTO>(user, HttpStatus.ACCEPTED);
        }catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        try{
            userFacade.deleteUser(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }


}
