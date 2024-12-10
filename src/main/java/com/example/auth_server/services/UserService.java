package com.example.auth_server.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.auth_server.Models.AuthRequest;
import com.example.auth_server.Models.UserDTO;
import com.example.auth_server.custom_exceptions.UnableToSaveUserException;
import com.example.auth_server.entity.User;

public interface UserService {

    UserDTO registerUser(User user) throws UnableToSaveUserException;

    String loginUser(AuthRequest authRequest);

    UserDTO getUser(String email);

    List<UserDTO> getAllUser();
    

}
