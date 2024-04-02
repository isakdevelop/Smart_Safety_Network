package com.smartsafetynetwork.api.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequestDto {
    private String username;
    private String password;
}
