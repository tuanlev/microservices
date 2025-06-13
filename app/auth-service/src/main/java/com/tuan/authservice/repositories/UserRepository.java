package com.tuan.authservice.repositories;

import org.springframework.security.core.userdetails.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends JpaRepository<User, Integer> {
   
    List<User> findByUsername(String username);
    
}
