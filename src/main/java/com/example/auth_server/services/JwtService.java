package com.example.auth_server.services;

import java.security.Key;
import java.util.Map;

public interface JwtService {


    String generateToken(String email);

    String createToken(Map<String, Object> claims, String email);

    Key getSignKey();
    
}
