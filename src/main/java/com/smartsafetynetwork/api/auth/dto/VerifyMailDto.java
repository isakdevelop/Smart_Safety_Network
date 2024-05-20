package com.smartsafetynetwork.api.auth.dto;

import lombok.Getter;

@Getter
public class VerifyMailDto {
    private String email;
    private String password;
}
