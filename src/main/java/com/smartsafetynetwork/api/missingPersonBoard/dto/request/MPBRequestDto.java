package com.smartsafetynetwork.api.missingPersonBoard.dto.request;

import lombok.Getter;

@Getter
public class MPBRequestDto {
    private String missingPersonId;
    private String userId;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
}
