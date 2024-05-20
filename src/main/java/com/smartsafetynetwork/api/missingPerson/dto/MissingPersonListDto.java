package com.smartsafetynetwork.api.missingPerson.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MissingPersonListDto {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String imgPath;

    @QueryProjection
    public MissingPersonListDto(String id, String name, int age, String gender, String address, String imgPath) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.imgPath = imgPath;
    }
}
