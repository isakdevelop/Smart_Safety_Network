package com.smartsafetynetwork.api.missingPersonBoard.dto.response;

import lombok.Getter;

@Getter
public class MPBListResponseDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String userName;

    public MPBListResponseDto(String id, String title, String content, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
    }
}
