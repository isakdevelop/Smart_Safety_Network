package com.smartsafetynetwork.api.police.dto;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class PoliceInfoDto extends ResponseDto {
    private String policeNumber;
    private String company;
    private String department;
}
