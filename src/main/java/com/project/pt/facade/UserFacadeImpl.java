package com.project.pt.facade;

import com.project.pt.dto.CustomerAssignmentDTO;
import com.project.pt.dto.user.PersistableUserDTO;
import com.project.pt.dto.user.ReadableUserDTO;
import com.project.pt.mapper.user.PersistableUserToUserMapper;
import com.project.pt.mapper.user.UserToReadableUserMapper;
import com.project.pt.model.User;
import com.project.pt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private PersistableUserToUserMapper mapper;

    @Autowired
    private UserToReadableUserMapper readableUserMapper;

    @Override
    public void saveUser(PersistableUserDTO persistableUser) {
        User user = mapper.map(persistableUser);
        userService.saveUser(user);
    }

    @Override
    public ReadableUserDTO getUser(String id) {
        User user = userService.getUser(UUID.fromString(id));
        return readableUserMapper.map(user);
    }

    @Override
    public List<ReadableUserDTO> getAllUsers(int page, int offset) {
        return userService.getAllUsers(page, offset)
                .stream()
                .map(readableUserMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
        UUID uuid = UUID.fromString(id);
        userService.deleteUser(uuid);
    }

    @Override
    public void updateUser(PersistableUserDTO persistableUserDTO) {
        userService.getUserByUsername(persistableUserDTO.getUsername());
        User user = mapper.map(persistableUserDTO);
        userService.saveUser(user);
    }
}
