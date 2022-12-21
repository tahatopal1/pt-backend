package com.project.pt.repository;

import com.project.pt.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.authorities a WHERE u.username = ?1")
    User findByUsernameQuery(String username);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.authorities a WHERE a.name = ?1")
    List<User> findAllTrainers(String authority, Pageable pageable);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.authorities a WHERE a.name != 'ADMIN'")
    List<User> findAllNonAdmins(Pageable pageable);

    Optional<User> findByUsername(String username);

    @Override
    User getById(UUID uuid);
}
