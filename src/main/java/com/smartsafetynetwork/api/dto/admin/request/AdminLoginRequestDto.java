package com.smartsafetynetwork.api.dto.admin.request;

import lombok.Getter;

@Getter
public class AdminLoginRequestDto {
    private String policeNumber;
    private String password;
}
