package com.example.auth_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.auth_server.Models.Role;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @NotBlank(message = "UserName is required")
    private String userName;

    @Column(name = "user_email", unique = true, nullable = false)
    @Email(message = "Email formate is not valid")
    @NotBlank(message = "email is required")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "password is required")
    private String password;

    @Column(name = "role")
    @NotBlank(message = "role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

    public String getRole(){
        return this.role.toString();
    }
    
}
