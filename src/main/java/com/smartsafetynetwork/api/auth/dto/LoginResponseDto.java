package com.smartsafetynetwork.api.auth.dto;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.enums.Role;
import lombok.Getter;

@Getter
public class LoginResponseDto extends ResponseDto {
    private String token;
    private String refreshToken;
    private int expirationTime;
    private String role;

    public LoginResponseDto(int status, String message, String token, String refreshToken, int expirationTime, Role role) {
        super(status, message);
        this.token = token;
        this.refreshToken = refreshToken;
        this.expirationTime = expirationTime;
        this.role = String.valueOf(role);
    }
}
