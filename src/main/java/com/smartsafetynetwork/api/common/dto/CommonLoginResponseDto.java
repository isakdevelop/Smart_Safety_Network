package com.smartsafetynetwork.api.common.dto;

import com.smartsafetynetwork.api.common.enums.Role;
import lombok.Getter;

@Getter
public class CommonLoginResponseDto extends ResponseDto {
    private String jwt;
    private int expirationTime;
    private String role;

    public CommonLoginResponseDto(int status, String message, String jwt, int expirationTime, Role role) {
        super(status, message);
        this.jwt = jwt;
        this.expirationTime = expirationTime;
        this.role = String.valueOf(role);
    }
}
