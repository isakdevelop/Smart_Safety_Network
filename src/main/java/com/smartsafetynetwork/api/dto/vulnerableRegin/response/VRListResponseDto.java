package com.smartsafetynetwork.api.dto.vulnerableRegin.response;

import java.util.List;
import lombok.Getter;

@Getter
public class VRListResponseDto {
    private List<PageDto> list;

    public VRListResponseDto(List<PageDto> list) {
        this.list = list;
    }
}
