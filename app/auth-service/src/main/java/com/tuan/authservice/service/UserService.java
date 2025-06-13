package com.tuan.authservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tuan.authservice.models.User;
import com.tuan.authservice.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(Optional<User> user) {
        if (user.isPresent()) {
         return userRepository.save(user.get());

        }
        
    }
}
