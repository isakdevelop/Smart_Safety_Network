package com.smartsafetynetwork.api.admin.dto.request;

import lombok.Getter;

@Getter
public class AdminSignUpRequestDto {
    private String policeNumber;
    private String name;
    private String password;
    private String phone;
    private String company;
    private String department;
}
