package com.project.pt.facade;

import com.project.pt.dto.CustomerAssignmentDTO;

public interface TrainerAssignmentFacade {

    void assignTrainerToUser(CustomerAssignmentDTO customerAssignmentDTO);

    void deleteTrainerFromTrainerList(CustomerAssignmentDTO customerAssignmentDTO);

}
