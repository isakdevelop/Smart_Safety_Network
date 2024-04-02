package com.smartsafetynetwork.api.dto.admin.response;

import lombok.Getter;

@Getter
public class AdminInfoResponseDto {
    private String name;
    private String phone;
    private String company;
    private String department;

    public AdminInfoResponseDto(String name, String phone, String company, String department) {
        this.name = name;
        this.phone = phone;
        this.company = company;
        this.department = department;
    }
}
