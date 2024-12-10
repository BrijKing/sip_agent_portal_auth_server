package com.example.auth_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_server.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

   Optional<User> findByEmail(String email);
    
}
