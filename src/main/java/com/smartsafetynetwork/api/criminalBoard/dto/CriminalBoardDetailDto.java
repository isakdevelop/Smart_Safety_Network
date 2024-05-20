package com.smartsafetynetwork.api.criminalBoard.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CriminalBoardDetailDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String userName;
    private String criminalName;

    @QueryProjection
    public CriminalBoardDetailDto(String id, String title, String content, String address, String latitude,
                                  String longitude, String userName, String criminalName) {
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
