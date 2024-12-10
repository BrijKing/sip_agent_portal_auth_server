package com.example.auth_server.Models;

import com.example.auth_server.annotation.ValidRoleAndAgentCode;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidRoleAndAgentCode
public class RegisterRequest {
    

    
    private String userName;

    private String email;

    private String password;

    private String role;

    private String agent_code;
}
