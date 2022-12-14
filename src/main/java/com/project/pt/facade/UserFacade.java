package com.project.pt.facade;

import com.project.pt.dto.CustomerAssignmentDTO;
import com.project.pt.dto.user.PersistableUserDTO;
import com.project.pt.dto.user.ReadableUserDTO;

import java.util.List;

public interface UserFacade {

    void saveUser(PersistableUserDTO persistableUser);

    ReadableUserDTO getUser(String id);

    List<ReadableUserDTO> getAllUsers(int page, int offset);

    void deleteUser(String id);

    void updateUser(PersistableUserDTO persistableUserDTO);

}
