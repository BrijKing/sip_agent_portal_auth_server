package com.example.auth_server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.auth_server.Models.UserDTO;
import com.example.auth_server.custom_exceptions.UnableToSaveUserException;
import com.example.auth_server.entity.User;
import com.example.auth_server.repository.UserRepository;
import com.example.auth_server.services.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO registerUser(User user) throws UnableToSaveUserException {

        try {

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public List<UserDTO> getAllUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUser'");
    }
    
}
