package com.smartsafetynetwork.api.vulnerableRegion.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class VRDetailDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String userName;

    @QueryProjection
    public VRDetailDto(String id, String title, String content, String address,
                       String latitude,
                       String longitude, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userName = userName;
    }
}
