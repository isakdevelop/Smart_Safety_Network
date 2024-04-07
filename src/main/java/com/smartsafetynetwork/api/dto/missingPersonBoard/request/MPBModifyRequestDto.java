package com.smartsafetynetwork.api.dto.missingPersonBoard.request;

import lombok.Getter;

@Getter
public class MPBModifyRequestDto {
    private String userId;
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
}
