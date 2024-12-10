package com.example.auth_server.validators;

import com.example.auth_server.Models.RegisterRequest;
import com.example.auth_server.annotation.ValidRoleAndAgentCode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleAndAgentCodeValidator implements ConstraintValidator<ValidRoleAndAgentCode, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest value, ConstraintValidatorContext context) {
        if (value == null){
            return true;
        }

        String role = value.getRole();
        String agentCode = value.getAgent_code();

        if ("agent".equalsIgnoreCase(role)) {
            return agentCode != null && !agentCode.isBlank();
        }
        else if ("user".equalsIgnoreCase(role)) {
            return agentCode == null || agentCode.isBlank();
        }

        return false;
        
        
    }
    
}
