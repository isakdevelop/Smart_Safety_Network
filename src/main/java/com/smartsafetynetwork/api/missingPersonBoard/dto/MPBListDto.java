package com.smartsafetynetwork.api.missingPersonBoard.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MPBListDto {
    private String id;
    private String title;
    private String content;
    private String userName;

    @QueryProjection
    public MPBListDto(String id, String title, String content, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
    }
}
