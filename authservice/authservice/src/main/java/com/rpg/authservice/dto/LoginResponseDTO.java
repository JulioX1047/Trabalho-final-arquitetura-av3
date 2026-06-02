package com.rpg.authservice.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String username;
    private String role;

    public LoginResponseDTO(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
    }
}