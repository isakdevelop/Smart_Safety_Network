package com.smartsafetynetwork.api.admin.dto.response;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
public class AdminInfoResponseDto extends ResponseDto {
    private String name;
    private String phone;
    private String company;
    private String department;
}
