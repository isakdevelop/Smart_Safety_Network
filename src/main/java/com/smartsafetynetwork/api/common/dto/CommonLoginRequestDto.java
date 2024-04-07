package com.smartsafetynetwork.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonLoginRequestDto {
    private String username;
    private String password;
}
