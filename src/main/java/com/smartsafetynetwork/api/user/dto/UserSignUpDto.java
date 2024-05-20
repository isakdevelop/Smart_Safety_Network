package com.smartsafetynetwork.api.user.dto;

import lombok.Getter;

@Getter
public class UserSignUpDto {
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
}
