package com.smartsafetynetwork.api.dto.admin.response;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
public class AdminResponseDto extends ResponseDto {
    private String id;
    private String policeNumber;
    private String name;
    private String password;
    private String phone;
    private String company;
    private String department;
}
