package com.smartsafetynetwork.api.vulnerableRegion.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class VRListDto {
    private String id;
    private String title;
    private String content;
    private String userName;

    @QueryProjection
    public VRListDto(String id, String title, String content, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
    }
}
