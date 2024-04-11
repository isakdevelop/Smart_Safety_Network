package com.smartsafetynetwork.api.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonLoginRequestDto {
    @NotBlank(message = "아이디가 올바르지 않습니다.")
    private String username;

    @NotBlank(message = "패스워드가 올바르지 않습니다.")
    private String password;
}
