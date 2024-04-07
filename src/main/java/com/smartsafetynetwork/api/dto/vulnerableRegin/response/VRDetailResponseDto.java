package com.smartsafetynetwork.api.dto.vulnerableRegin.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class VRDetailResponseDto {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private String address;
    private String latitude;
    private String longitude;
    private String name;

    public VRDetailResponseDto(String id, String title, String content, LocalDateTime createAt, String address,
                               String latitude,
                               String longitude, String name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
}
