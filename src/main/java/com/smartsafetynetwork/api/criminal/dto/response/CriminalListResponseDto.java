package com.smartsafetynetwork.api.criminal.dto.response;

import lombok.Getter;

@Getter
public class CriminalListResponseDto {
    private String id;
    private String name;
    private String age;
    private String crime;
    private String imgPath;

    public CriminalListResponseDto(String id, String name, String age, String crime, String imgPath) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.crime = crime;
        this.imgPath = imgPath;
    }
}
