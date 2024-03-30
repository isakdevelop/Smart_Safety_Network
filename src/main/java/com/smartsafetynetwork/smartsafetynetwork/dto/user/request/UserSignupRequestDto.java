package com.smartsafetynetwork.smartsafetynetwork.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignupRequestDto {
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String gender;
    private String email;
    private String phone;
}
