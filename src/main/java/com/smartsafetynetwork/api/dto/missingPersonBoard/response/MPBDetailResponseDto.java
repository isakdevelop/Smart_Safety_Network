package com.smartsafetynetwork.api.dto.missingPersonBoard.response;

import lombok.Getter;

@Getter
public class MPBDetailResponseDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String user_name;
    private String missingPerson_name;

    public MPBDetailResponseDto(String id, String title, String content, String address, String latitude, String longitude,
                                String user_name, String missingPerson_name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user_name = user_name;
        this.missingPerson_name = missingPerson_name;
    }
}
