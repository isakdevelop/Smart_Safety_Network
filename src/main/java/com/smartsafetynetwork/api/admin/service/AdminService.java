package com.smartsafetynetwork.api.admin.service;

import com.smartsafetynetwork.api.admin.dto.request.AdminModifyRequestDto;
import com.smartsafetynetwork.api.admin.dto.request.AdminSignUpRequestDto;
import com.smartsafetynetwork.api.common.dto.ResponseDto;

import com.smartsafetynetwork.api.admin.dto.response.AdminInfoResponseDto;

public interface AdminService {
    ResponseDto signup(AdminSignUpRequestDto adminRequestDto);

    AdminInfoResponseDto info();

    ResponseDto modify(AdminModifyRequestDto adminRequestDto);

    ResponseDto delete();
}
