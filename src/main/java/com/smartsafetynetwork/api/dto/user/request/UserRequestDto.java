package com.smartsafetynetwork.api.dto.user.request;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String id;
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String gender;
    private String email;
    private String phone;
}
