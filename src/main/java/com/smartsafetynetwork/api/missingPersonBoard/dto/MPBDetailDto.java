package com.smartsafetynetwork.api.missingPersonBoard.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MPBDetailDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String userName;
    private String missingPersonName;

    @QueryProjection
    public MPBDetailDto(String id, String title, String content, String address, String latitude, String longitude,
                        String userName, String missingPersonName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userName = userName;
        this.missingPersonName = missingPersonName;
    }
}
