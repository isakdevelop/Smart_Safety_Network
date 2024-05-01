package com.smartsafetynetwork.api.vulnerableRegion.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PageDto {
    private String id;
    private String title;
    private String content;
    private String name;
    private LocalDateTime creatAt;

    public PageDto(String id, String title, String content, String name, LocalDateTime creatAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.creatAt = creatAt;
    }
}
