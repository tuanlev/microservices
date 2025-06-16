package com.tuan.authservice.repositories;

import org.springframework.stereotype.Repository;

import com.tuan.authservice.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {
   Optional<User> findUserByUsername(String username) ;
   Optional<User> findUserById(Long id);
   @Query(value = "SELECT * FROM users u WHERE u.username =:username",nativeQuery=true)
   Optional<User> checkRegistrationConditions(String username);
}
