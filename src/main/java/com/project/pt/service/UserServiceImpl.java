package com.project.pt.service;

import com.project.pt.model.User;
import com.project.pt.model.nonmapped.SecurityCustomer;
import com.project.pt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByUsernameQuery(username))
                .orElseThrow(() -> new UsernameNotFoundException("User details not found for the user: " + username));
        return new SecurityCustomer(user);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(UUID uuid) {
        return userRepository.getReferenceById(uuid);
    }

    @Override
    public List<User> getAllUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public void deleteUser(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Override
    public User getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsernameQuery(username))
                .orElseThrow(() -> new RuntimeException("A problem has occurred getting user information by username: " + username));
    }
}
