package com.tuan.authservice.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.tuan.authservice.repositories.UserRepository;
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Override 
    public UserDetails loadUserByUsername(String username) {
        
        return userRepository.getUserByUsername(); // Replace with actual user details retrieval logic
    }
}
