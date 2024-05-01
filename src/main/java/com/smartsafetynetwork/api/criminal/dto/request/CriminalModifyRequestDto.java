package com.smartsafetynetwork.api.criminal.dto.request;

import lombok.Getter;

@Getter
public class CriminalModifyRequestDto {
    private String criminal_id;
    private String name;
    private String registrationPlace;
    private String address;
    private String crime;
}
