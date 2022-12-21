package com.project.pt.facade;

import com.project.pt.dto.CustomerAssignmentDTO;
import com.project.pt.model.User;
import com.project.pt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TrainerAssignmentFacadeImpl implements TrainerAssignmentFacade {

    @Autowired
    private UserService userService;

    @Override
    public void assignTrainerToCustomer(CustomerAssignmentDTO customerAssignmentDTO) {
        assignTrainer(customerAssignmentDTO.getTrainer(), customerAssignmentDTO.getCustomer());
    }

    @Override
    public void assignTrainerToCustomer(String trainerUsername) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        assignTrainer(trainerUsername, currentUsername);
    }

    @Override
    public void removeTrainerFromCustomer(CustomerAssignmentDTO customerAssignmentDTO) {
        discardTrainer(customerAssignmentDTO.getTrainer(), customerAssignmentDTO.getCustomer());
    }

    @Override
    public void removeTrainerFromCustomer(String trainerUsername) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        discardTrainer(trainerUsername, currentUsername);
    }

    private void discardTrainer(String trainerUsername, String currentUsername) {
        User customer = userService.getUserByUsername(currentUsername);
        User trainer = userService.getUserByUsername(trainerUsername);
        customer.removeTrainer(trainer);
        userService.saveUser(customer);
    }

    private void assignTrainer(String trainerUsername, String currentUsername) {
        User customer = userService.getUserByUsername(currentUsername);
        User trainer = userService.getUserByUsername(trainerUsername);
        customer.addTrainer(trainer);
        userService.saveUser(customer);
    }
}
