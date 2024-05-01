package com.smartsafetynetwork.api.missingPerson.dto.response;

import lombok.Getter;

@Getter
public class MissingPersonListResponseDto {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String imgPath;

    public MissingPersonListResponseDto(String id, String name, int age, String gender, String address,
                                        String imgPath) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.imgPath = imgPath;
    }
}
