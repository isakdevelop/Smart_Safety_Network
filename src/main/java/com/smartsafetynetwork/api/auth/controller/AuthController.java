package com.smartsafetynetwork.api.auth.controller;

import com.smartsafetynetwork.api.auth.dto.FindMailDto;
import com.smartsafetynetwork.api.auth.dto.LogoutDto;
import com.smartsafetynetwork.api.auth.dto.LoginRequestDto;
import com.smartsafetynetwork.api.auth.dto.LoginResponseDto;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.auth.dto.VerifyMailDto;
import com.smartsafetynetwork.api.auth.service.AuthService;
import com.smartsafetynetwork.api.auth.model.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<LoginResponseDto> getToken(@RequestBody RefreshToken refreshToken) {
        return ResponseEntity.ok(authService.generateAccessToken(refreshToken));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> logout(@RequestBody LogoutDto logoutDto) {
        return ResponseEntity.ok(authService.logout(logoutDto));
    }

    @PostMapping("/findMail")
    public ResponseEntity<ResponseDto> findMail(@RequestBody FindMailDto findMailDto) {
        return ResponseEntity.ok(authService.findMail(findMailDto));
    }

    @PostMapping("/verifyMail")
    public ResponseEntity<ResponseDto> verifyMail(@RequestBody VerifyMailDto verifyMailDto) {
        return ResponseEntity.ok(authService.verifyMail(verifyMailDto));
    }
}
