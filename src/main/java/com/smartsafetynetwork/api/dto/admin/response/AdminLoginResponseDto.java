package com.smartsafetynetwork.api.dto.admin.response;

import lombok.Getter;

@Getter
public class AdminLoginResponseDto {
    private String id;
    private String name;

    public AdminLoginResponseDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
