package com.smartsafetynetwork.api.criminalBoard.dto.response;

import lombok.Getter;

@Getter
public class CBResponseDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String userName;
    private String crimeName;

    public CBResponseDto(String id, String title, String content, String address, String latitude, String longitude,
                         String userName, String crimeName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userName = userName;
        this.crimeName = crimeName;
    }
}
