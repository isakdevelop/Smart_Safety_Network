package com.smartsafetynetwork.api.vulnerableRegion.dto.response;

import lombok.Getter;

@Getter
public class VRListResponseDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String name;

    public VRListResponseDto(String id, String title, String content, String name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
    }

    public VRListResponseDto(String id, String title, String content, String address, String latitude, String longitude,
                             String name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
}
