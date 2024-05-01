package com.smartsafetynetwork.api.user.dto.response;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class UserInfoResponseDto extends ResponseDto {
    private String name;
    private String birthday;
    private String gender;
    private String email;
    private String phone;
}
