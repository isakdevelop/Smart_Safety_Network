package com.smartsafetynetwork.api.missingPerson.dto;

import lombok.Getter;

@Getter
public class MissingPersonModifyDto {
    private String name;
    private String gender;
    private int age;
    private String location;
    private String date;
    private String latitude;
    private String longitude;
    private String address;
    private Double height;
    private Double weight;
    private String physique;
    private String faceShape;
    private String hairColor;
    private String hairShape;
    private String cloth;
    private String missingPersonId;
}
