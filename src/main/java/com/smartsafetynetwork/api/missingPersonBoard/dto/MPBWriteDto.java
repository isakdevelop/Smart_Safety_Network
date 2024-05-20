package com.smartsafetynetwork.api.missingPersonBoard.dto;

import lombok.Getter;

@Getter
public class MPBWriteDto {
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String missingPersonId;
}
