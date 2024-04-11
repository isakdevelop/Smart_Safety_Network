package com.smartsafetynetwork.api.service.auth;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.auth.request.AuthMailRequestDto;

public interface AuthService {
    ResponseDto authCheckMail(AuthMailRequestDto authMailRequestDto);

    ResponseDto authMail(AuthMailRequestDto authMailRequestDto);
}
