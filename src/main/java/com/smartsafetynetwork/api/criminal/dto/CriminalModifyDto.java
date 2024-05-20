package com.smartsafetynetwork.api.criminal.dto;

import lombok.Getter;

@Getter
public class CriminalModifyDto {
    private String criminal_id;
    private String name;
    private String registrationPlace;
    private String address;
    private String crime;
}
