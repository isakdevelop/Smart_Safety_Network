package com.smartsafetynetwork.api.dto.criminal.request;

import lombok.Getter;

@Getter
public class CriminalModifyRequestDto {
    private String id;
    private String criminalId;
    private String afterName;
    private String afterRegistrationPlace;
    private String afterAddress;
    private String afterCrime;
}
