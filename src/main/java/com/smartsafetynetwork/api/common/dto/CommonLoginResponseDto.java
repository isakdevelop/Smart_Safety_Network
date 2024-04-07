package com.smartsafetynetwork.api.common.dto;

import com.smartsafetynetwork.api.dto.ResponseDto;
import lombok.Getter;

@Getter
public class CommonLoginResponseDto extends ResponseDto {
    private String jwt;
    private int expirationTime;
    private String id;

    public CommonLoginResponseDto(int status, String message, String jwt, int expirationTime, String id) {
        super(status, message);
        this.jwt = jwt;
        this.expirationTime = expirationTime;
        this.id = id;
    }
}
