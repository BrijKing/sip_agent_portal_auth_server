package com.example.auth_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_server.Models.AuthRequest;
import com.example.auth_server.Models.RegisterRequest;
import com.example.auth_server.Models.ResponseDTO;
import com.example.auth_server.Models.UserDTO;
import com.example.auth_server.custom_exceptions.UnableToSaveUserException;
import com.example.auth_server.entity.User;
import com.example.auth_server.services.UserService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid RegisterRequest registerRequest) throws UnableToSaveUserException {

        UserDTO userDTO = UserDTO.builder()
                            .email(registerRequest.getEmail())
                            .userName(registerRequest.getUserName())
                            .build();

        System.out.println(registerRequest.toString());

        // if(registerRequest.getRole().equals("agent") && (registerRequest.getAgent_code() == null)){

        //     return new ResponseEntity<ResponseDTO>(ResponseDTO.builder()
        //                                                     .message("agent_code is required ")
        //                                                     .build(),HttpStatus.BAD_REQUEST);
        // }

        User user = User.builder()
                        .email(registerRequest.getEmail())
                        .password(registerRequest.getPassword())
                        .userName(registerRequest.getUserName())
                        .role(registerRequest.getRole())
                        .build();


        
        UserDTO savedUser =  userService.registerUser(user);

        return new ResponseEntity<>(ResponseDTO.builder()
                                                .object(savedUser)
                                                .httpStatus(HttpStatus.OK)
                                                .message("user saved successfully !!")
                                                .build(), HttpStatus.OK);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody AuthRequest authRequest) {

    
        Map<String, String> response = new HashMap<>();

        response.put("token",userService.loginUser(authRequest));


        return new ResponseEntity<>(ResponseDTO.builder()
                                                .object(response)
                                                .httpStatus(HttpStatus.OK)
                                                .message("user login successfully !!")
                                                .build(),HttpStatus.OK);       
    }
    
    
    
}
