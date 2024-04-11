package com.smartsafetynetwork.api.controller.auth;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.auth.request.AuthMailRequestDto;
import com.smartsafetynetwork.api.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/checkMail")
    public ResponseDto authCheckMail(@RequestBody AuthMailRequestDto authMailRequestDto) {
        return authService.authCheckMail(authMailRequestDto);
    }

    @PostMapping("/authMail")
    public ResponseDto authMail(@RequestBody AuthMailRequestDto authMailRequestDto) {
        return authService.authMail(authMailRequestDto);
    }
}
