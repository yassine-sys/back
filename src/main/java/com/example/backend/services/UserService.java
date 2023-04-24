package com.example.backend.services;

import com.example.backend.entities.RepRapport;
import com.example.backend.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Object customResponse(Long id);

    User addUser(User user);
    User editUser(User user);

    List<User> getListUser();

    void deleteUser(Long id);

    User findById(Long id);

    Optional<User> findByUsername(String username);

    void assignFunc(Long id, List<RepRapport> rap);

    void detachRep(Long id,RepRapport rep);
}

