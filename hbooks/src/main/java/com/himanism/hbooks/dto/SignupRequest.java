package com.himanism.hbooks.dto;

import com.himanism.hbooks.enums.Role;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private Role role;

    public SignupRequest() {}

    public SignupRequest(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

 
}
