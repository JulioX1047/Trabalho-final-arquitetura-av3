package com.rpg.authservice.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}