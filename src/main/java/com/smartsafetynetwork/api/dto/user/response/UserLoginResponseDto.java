package com.smartsafetynetwork.api.dto.user.response;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private String id;
    private String name;

    public UserLoginResponseDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
