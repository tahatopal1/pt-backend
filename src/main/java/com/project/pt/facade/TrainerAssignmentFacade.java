package com.project.pt.facade;

import com.project.pt.dto.CustomerAssignmentDTO;

public interface TrainerAssignmentFacade {

    void assignTrainerToCustomer(CustomerAssignmentDTO customerAssignmentDTO);

    void assignTrainerToCustomer(String trainer);

    void removeTrainerFromCustomer(CustomerAssignmentDTO customerAssignmentDTO);

    void removeTrainerFromCustomer(String trainer);

}
