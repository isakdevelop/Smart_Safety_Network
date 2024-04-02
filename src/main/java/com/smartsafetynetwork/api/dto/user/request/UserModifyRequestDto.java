package com.smartsafetynetwork.api.dto.user.request;

import lombok.Getter;

@Getter
public class UserModifyRequestDto {
    private String id;
    private String afterEmail;
    private String afterPhone;
}
