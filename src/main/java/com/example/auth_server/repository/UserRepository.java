package com.example.auth_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_server.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
