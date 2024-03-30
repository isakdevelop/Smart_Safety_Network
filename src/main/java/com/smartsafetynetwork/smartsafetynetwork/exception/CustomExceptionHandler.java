package com.smartsafetynetwork.smartsafetynetwork.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<CustomExceptionModel> handleCustomException(CustomException customException) {
        CustomExceptionModel customExceptionModel = new CustomExceptionModel(
                customException.getStatus(),
                customException.getMessage()
        );
        return new ResponseEntity<>(customExceptionModel, HttpStatus.valueOf(customException.getStatus()));
    }
}
