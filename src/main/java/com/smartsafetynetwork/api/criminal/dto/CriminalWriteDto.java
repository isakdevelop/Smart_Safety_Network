package com.smartsafetynetwork.api.criminal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CriminalWriteDto {
    private String name;
    private Integer age;
    private String crime;
    private String registrationPlace;
    private String address;
}
