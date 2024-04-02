package com.smartsafetynetwork.api.dto.admin.request;

import lombok.Getter;

@Getter
public class AdminSignupRequestDto {
    private String policeNumber;
    private String name;
    private String password;
    private String phone;
    private String company;
    private String department;
}
