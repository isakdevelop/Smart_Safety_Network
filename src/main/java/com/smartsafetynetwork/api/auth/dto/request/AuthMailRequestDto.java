package com.smartsafetynetwork.api.auth.dto.request;

import lombok.Getter;

@Getter
public class AuthMailRequestDto {
    private String email;
    private String password;
}
