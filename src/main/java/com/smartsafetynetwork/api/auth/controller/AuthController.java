package com.smartsafetynetwork.api.auth.controller;

import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.auth.dto.request.AuthMailRequestDto;
import com.smartsafetynetwork.api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/userLogin")
    public ResponseEntity<CommonLoginResponseDto> userLogin(@RequestBody CommonLoginRequestDto commonLoginRequestDto) {
        return ResponseEntity.ok(authService.userLogin(commonLoginRequestDto));
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<CommonLoginResponseDto> adminLogin(@RequestBody CommonLoginRequestDto commonLoginRequestDto) {
        return ResponseEntity.ok(authService.adminLogin(commonLoginRequestDto));
    }

    @PostMapping("/checkMail")
    public ResponseEntity<ResponseDto> authCheckMail(@RequestBody AuthMailRequestDto authMailRequestDto) {
        return ResponseEntity.ok(authService.authCheckMail(authMailRequestDto));
    }

    @PostMapping("/authMail")
    public ResponseEntity<ResponseDto> authMail(@RequestBody AuthMailRequestDto authMailRequestDto) {
        return ResponseEntity.ok(authService.authMail(authMailRequestDto));
    }
}
