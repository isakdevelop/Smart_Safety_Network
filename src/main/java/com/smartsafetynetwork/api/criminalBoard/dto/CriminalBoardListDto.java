package com.smartsafetynetwork.api.criminalBoard.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class CriminalBoardListDto {
    private String id;
    private String title;
    private String content;
    private String userName;
    private String criminalName;


    @QueryProjection
    public CriminalBoardListDto(String id, String title, String content, String userName, String criminalName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.criminalName = criminalName;
    }
}
