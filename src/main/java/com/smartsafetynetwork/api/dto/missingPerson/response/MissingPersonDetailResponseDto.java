package com.smartsafetynetwork.api.dto.missingPerson.response;

import lombok.Getter;

@Getter
public class MissingPersonDetailResponseDto {
    private String id;
    private String name;
    private String gender;
    private int age;
    private String location;
    private String date;
    private String latitude;
    private String longitude;
    private String address;
    private String height;
    private String weight;
    private String physique;
    private String faceShape;
    private String hairColor;
    private String hairShape;
    private String cloth;
    private String imagePath;

    public MissingPersonDetailResponseDto(String id, String name, String gender, int age, String location, String date,
                                          String latitude, String longitude, String address, String height,
                                          String weight,
                                          String physique, String faceShape, String hairColor, String hairShape,
                                          String cloth, String imagePath) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.location = location;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.height = height;
        this.weight = weight;
        this.physique = physique;
        this.faceShape = faceShape;
        this.hairColor = hairColor;
        this.hairShape = hairShape;
        this.cloth = cloth;
        this.imagePath = imagePath;
    }
}
