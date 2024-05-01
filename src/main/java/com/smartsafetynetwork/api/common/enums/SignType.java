package com.smartsafetynetwork.api.common.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SignType {
    SSN("Smart_Safety_Network"),
    NAVER("Naver"),
    KAKAO("Kakao"),
    GOOGLE("google")
    ;

    private final String type;
}
