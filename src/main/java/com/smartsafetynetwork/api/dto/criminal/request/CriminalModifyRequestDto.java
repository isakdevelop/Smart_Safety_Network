package com.smartsafetynetwork.api.dto.criminal.request;

import lombok.Getter;

@Getter
public class CriminalModifyRequestDto {
    private String user_id;
    private String criminal_id;
    private String afterName;
    private String afterRegistrationPlace;
    private String afterAddress;
    private String afterCrime;
}
