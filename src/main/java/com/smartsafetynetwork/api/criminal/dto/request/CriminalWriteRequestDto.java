package com.smartsafetynetwork.api.criminal.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class CriminalWriteRequestDto {
    private String name;
    private String age;
    private String crime;
    private String registrationPlace;
    private String address;
    private MultipartFile image;
}
