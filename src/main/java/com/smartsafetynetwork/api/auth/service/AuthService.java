package com.smartsafetynetwork.api.auth.service;

import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.auth.dto.request.AuthMailRequestDto;

public interface AuthService {
    CommonLoginResponseDto userLogin(CommonLoginRequestDto commonLoginRequestDto);

    CommonLoginResponseDto adminLogin(CommonLoginRequestDto commonLoginRequestDto);

    ResponseDto authCheckMail(AuthMailRequestDto authMailRequestDto);

    ResponseDto authMail(AuthMailRequestDto authMailRequestDto);
}
