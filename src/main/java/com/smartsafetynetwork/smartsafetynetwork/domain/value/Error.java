package com.smartsafetynetwork.smartsafetynetwork.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Error {
    BAD_REQUEST(400, "잘못된 형식 입니다."),
    FORBIDDEN(403, "접근 권한이 존재하지 않습니다."),
    NOT_FOUND(404, "존재하지 않는 값 입니다."),
    CONFLICT(409, "이미 중복 입니다.")
    ;

    private final int status;
    private final String message;
}