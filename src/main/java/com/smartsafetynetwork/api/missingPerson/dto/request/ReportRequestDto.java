package com.smartsafetynetwork.api.missingPerson.dto.request;

import lombok.Getter;

@Getter
public class ReportRequestDto {
    private String title;
    private String content;
    private String password;
    private String address;
    private String latitude;
    private String longitude;
    private String userId;
}
