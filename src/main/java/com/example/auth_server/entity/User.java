package com.example.auth_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @NotBlank(message = "UserName is required")
    private String userName;

    @Column(name = "user_email")
    @Email(message = "Email formate is not valid")
    @NotBlank(message = "email is required")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "password is required")
    private String password;

    @Column(name = "role")
    @NotBlank(message = "role is required")
    private String role;
    
}
