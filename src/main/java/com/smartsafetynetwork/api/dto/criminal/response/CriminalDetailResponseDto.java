package com.smartsafetynetwork.api.dto.criminal.response;

import lombok.Getter;

@Getter
public class CriminalDetailResponseDto {
    private String id;
    private String name;
    private String crime;
    private String registration_place;
    private String address;
    private String imgPath;

    public CriminalDetailResponseDto(String id, String name, String crime, String registration_place, String address,
                                     String imgPath) {
        this.id = id;
        this.name = name;
        this.crime = crime;
        this.registration_place = registration_place;
        this.address = address;
        this.imgPath = imgPath;
    }
}
