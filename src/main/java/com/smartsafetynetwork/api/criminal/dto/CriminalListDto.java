package com.smartsafetynetwork.api.criminal.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CriminalListDto {
    private String id;
    private String name;
    private String crime;
    private String imagePath;

    @QueryProjection
    public CriminalListDto(String id, String name, String crime, String imagePath) {
        this.id = id;
        this.name = name;
        this.crime = crime;
        this.imagePath = imagePath;
    }
}
