package com.smartsafetynetwork.smartsafetynetwork.common;

import lombok.Getter;

@Getter
public class ResponseMessage {
    private int status;
    private String message;

    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }
}