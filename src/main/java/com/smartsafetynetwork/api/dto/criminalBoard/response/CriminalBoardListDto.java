package com.smartsafetynetwork.api.dto.criminalBoard.response;

import lombok.Getter;

@Getter
public class CriminalBoardListDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String userName;
    private String criminalName;

    public CriminalBoardListDto(String id, String title, String content, String userName, String criminalName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.criminalName = criminalName;
    }

    public CriminalBoardListDto(String id, String title, String content, String address, String latitude,
                                String longitude,
                                String userName, String criminalName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userName = userName;
        this.criminalName = criminalName;
    }
}
