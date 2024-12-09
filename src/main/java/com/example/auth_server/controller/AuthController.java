package com.example.auth_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_server.Models.AuthRequest;
import com.example.auth_server.Models.UserDTO;
import com.example.auth_server.custom_exceptions.UnableToSaveUserException;
import com.example.auth_server.entity.User;
import com.example.auth_server.repository.UserRepository;
import com.example.auth_server.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<UserDTO> registerUser(@RequestBody User user) throws UnableToSaveUserException {
        
        UserDTO savedUser =  userService.registerUser(user);

        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequest authRequest) {

        return userService.loginUser(authRequest);
    }
    
    
    
}
