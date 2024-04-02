package com.smartsafetynetwork.api.dto.missingPerson.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class MissingPersonWriteRequestDto {
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
    private MultipartFile image;
}
