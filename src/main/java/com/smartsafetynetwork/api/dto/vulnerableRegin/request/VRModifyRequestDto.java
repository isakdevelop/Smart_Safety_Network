package com.smartsafetynetwork.api.dto.vulnerableRegin.request;

import lombok.Getter;

@Getter
public class VRModifyRequestDto {
    private String id;
    private String title;
    private String content;
    private String address;
    private String latitude;
    private String longitude;
    private String name;
}
