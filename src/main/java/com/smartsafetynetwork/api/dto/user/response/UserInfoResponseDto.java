package com.smartsafetynetwork.api.dto.user.response;

import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private String name;
    private String birthday;
    private String gender;
    private String email;
    private String phone;

    public UserInfoResponseDto(String name, String birthday, String gender, String email, String phone) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }
}
