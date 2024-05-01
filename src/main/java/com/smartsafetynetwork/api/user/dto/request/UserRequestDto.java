package com.smartsafetynetwork.api.user.dto.request;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String gender;
    private String email;
    private String phone;
}
