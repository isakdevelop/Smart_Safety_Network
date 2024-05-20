package com.smartsafetynetwork.api.criminal.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CriminalDetailDto {
    private String id;
    private String name;
    private Integer age;
    private String crime;
    private String registrationPlace;
    private String address;
    private String imagePath;

    @QueryProjection
    public CriminalDetailDto(String id, String name, Integer age, String crime, String registrationPlace, String address, String imagePath) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.crime = crime;
        this.registrationPlace = registrationPlace;
        this.address = address;
        this.imagePath = imagePath;
    }
}
