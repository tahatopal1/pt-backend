package com.project.pt.service;

import com.project.pt.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    User getUser(UUID uuid);

    List<User> getAllUsers(int page, int offset);

    void deleteUser(UUID uuid);

    User getUserByUsername(String username);

}
