package com.smartsafetynetwork.api.user.dto.response;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class UserInfoDto extends ResponseDto {
    private String name;
    private String email;
    private String phone;
}
