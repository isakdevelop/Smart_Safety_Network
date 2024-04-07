package com.smartsafetynetwork.api.exception;

import com.smartsafetynetwork.api.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ResponseDto> handleCustomException(CustomException customException) {
        ResponseDto responseDto = new ResponseDto(
                customException.getStatus(),
                customException.getMessage()
        );
        return new ResponseEntity<>(responseDto, HttpStatus.valueOf(customException.getStatus()));
    }
}
