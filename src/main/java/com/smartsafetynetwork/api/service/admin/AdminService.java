package com.smartsafetynetwork.api.service.admin;

import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.admin.request.AdminLoginRequestDto;
import com.smartsafetynetwork.api.dto.admin.request.AdminModifyRequestDto;
import com.smartsafetynetwork.api.dto.admin.request.AdminSignupRequestDto;
import com.smartsafetynetwork.api.dto.admin.response.AdminInfoResponseDto;
import com.smartsafetynetwork.api.dto.admin.response.AdminLoginResponseDto;

public interface AdminService {
    ResponseMessage signup(AdminSignupRequestDto adminSignupRequestDto);

    AdminLoginResponseDto login(AdminLoginRequestDto adminLoginRequestDto);

    ResponseMessage modify(AdminModifyRequestDto adminModifyRequestDto);

    AdminInfoResponseDto info(RequestId requestId);
}
