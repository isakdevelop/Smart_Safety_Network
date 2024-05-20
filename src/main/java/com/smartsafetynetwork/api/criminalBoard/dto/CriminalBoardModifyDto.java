package com.smartsafetynetwork.api.criminalBoard.dto;

import lombok.Getter;

@Getter
public class CriminalBoardModifyDto {
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String criminalId;
}
