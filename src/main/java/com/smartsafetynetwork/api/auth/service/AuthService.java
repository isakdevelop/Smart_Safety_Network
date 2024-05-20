package com.smartsafetynetwork.api.auth.service;

import com.smartsafetynetwork.api.auth.dto.FindMailDto;
import com.smartsafetynetwork.api.auth.dto.LogoutDto;
import com.smartsafetynetwork.api.auth.dto.LoginRequestDto;
import com.smartsafetynetwork.api.auth.dto.LoginResponseDto;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.auth.dto.VerifyMailDto;
import com.smartsafetynetwork.api.auth.model.RefreshToken;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    ResponseDto logout(LogoutDto logoutDto);

    ResponseDto findMail(FindMailDto findMailDto);

    ResponseDto verifyMail(VerifyMailDto verifyMailDto);

    LoginResponseDto generateAccessToken(RefreshToken refreshToken);
}
