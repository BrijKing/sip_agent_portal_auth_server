package com.example.auth_server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.example.auth_server.validators.RoleAndAgentCodeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RoleAndAgentCodeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRoleAndAgentCode {

    String message() default "Invalid role or agent_code configuration";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
