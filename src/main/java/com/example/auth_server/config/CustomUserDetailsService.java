package com.example.auth_server.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.auth_server.entity.User;
import com.example.auth_server.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Optional<User> user = userRepository.findByEmail(email);

        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("email not found "+ email));   
    }
    
}
