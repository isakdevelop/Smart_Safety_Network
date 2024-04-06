package com.smartsafetynetwork.api.dto.user.response;

import com.smartsafetynetwork.api.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class UserResponseDto extends ResponseDto {
    private String username;
    private String password;
    private String name;
    private String birthday;
    private String gender;
    private String email;
    private String phone;
}
