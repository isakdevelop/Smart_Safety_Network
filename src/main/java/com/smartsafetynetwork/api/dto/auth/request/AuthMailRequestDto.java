package com.smartsafetynetwork.api.dto.auth.request;

import lombok.Getter;

@Getter
public class AuthMailRequestDto {
    private String email;
    private String password;
}
