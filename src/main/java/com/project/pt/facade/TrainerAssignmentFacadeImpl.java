package com.project.pt.facade;

import com.project.pt.dto.CustomerAssignmentDTO;
import com.project.pt.model.User;
import com.project.pt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrainerAssignmentFacadeImpl implements TrainerAssignmentFacade {

    @Autowired
    private UserService userService;

    @Override
    public void assignTrainerToUser(CustomerAssignmentDTO customerAssignmentDTO) {
//        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUsername(customerAssignmentDTO.getCustomer());
        User trainer = userService.getUserByUsername(customerAssignmentDTO.getTrainer());
        currentUser.addTrainer(trainer);
        userService.saveUser(currentUser);
    }

    @Override
    public void deleteTrainerFromTrainerList(CustomerAssignmentDTO customerAssignmentDTO) {
//        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUsername(customerAssignmentDTO.getCustomer());
        User trainer = userService.getUserByUsername(customerAssignmentDTO.getTrainer());
        currentUser.removeTrainer(trainer);
        userService.saveUser(currentUser);
    }
}
