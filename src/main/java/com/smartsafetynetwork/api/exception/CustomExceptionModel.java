package com.smartsafetynetwork.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomExceptionModel {
    private int errorCode;
    private String errorMessage;
}
