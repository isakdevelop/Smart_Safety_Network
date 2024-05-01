package com.smartsafetynetwork.api.criminalBoard.dto.request;

import lombok.Getter;

@Getter
public class CBRequestDto {
    private String id;
    private String criminalId;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
}
