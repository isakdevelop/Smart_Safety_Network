package com.smartsafetynetwork.api.vulnerableRegion.dto;

import lombok.Getter;

@Getter
public class VRModifyDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
}
