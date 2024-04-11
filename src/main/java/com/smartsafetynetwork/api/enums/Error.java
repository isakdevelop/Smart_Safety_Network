package com.smartsafetynetwork.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum Error {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "잘못된 형식 입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "접근 권한이 존재하지 않습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 값 입니다."),
    CONFLICT(HttpStatus.CONFLICT.value(), "이미 중복 입니다."),
    ;

    private final int status;
    private final String message;
}