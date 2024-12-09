package com.example.auth_server.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_server.Models.AuthRequest;
import com.example.auth_server.Models.UserDTO;
import com.example.auth_server.custom_exceptions.UnableToSaveUserException;
import com.example.auth_server.entity.User;
import com.example.auth_server.repository.UserRepository;
import com.example.auth_server.services.JwtService;
import com.example.auth_server.services.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @Override
    public UserDTO registerUser(User user) throws UnableToSaveUserException {

        try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User savedUser = userRepository.save(user);

            return UserDTO.builder()
            .email(savedUser.getEmail())
            .userName(savedUser.getUserName())
            .build();
            
        } catch (Exception e) {

            throw new UnableToSaveUserException(e.getMessage());
           
        }
        
        
    }

    @Override
    public UserDTO getUser(String email) {

        User user = userRepository
        .findByEmail(email)
        .orElseThrow(
            () -> new UsernameNotFoundException(
                "User name not found with email ")
                );

        return UserDTO
        .builder()
        .email(user.getEmail())
        .userName(user.getUserName())
        .build();
    }

    @Override
    public List<UserDTO> getAllUser() {
        
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> UserDTO
        .builder()
        .email(user.getEmail())
        .userName(user.getUserName())
        .build()).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> loginUser(AuthRequest authRequest) {
        
        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        if (!authenticate.isAuthenticated()){

            return new ResponseEntity<String>("User is not valid. ", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<String>(jwtService.generateToken(authRequest.getEmail()),HttpStatus.OK);

    }
    
}
