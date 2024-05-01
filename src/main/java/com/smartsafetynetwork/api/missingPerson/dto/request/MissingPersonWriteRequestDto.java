package com.smartsafetynetwork.api.missingPerson.dto.request;

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
    private Double height;
    private Double weight;
    private String physique;
    private String faceShape;
    private String hairColor;
    private String hairShape;
    private String cloth;
    private MultipartFile image;
}
