package com.smartsafetynetwork.smartsafetynetwork.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private int status;
    private String message;
}
