package com.smartsafetynetwork.api.vulnerableRegion.dto.request;

import lombok.Getter;

@Getter
public class VRWriteRequestDto {
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
}
