package com.smartsafetynetwork.api.service.admin;

import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.admin.request.AdminRequestDto;

import com.smartsafetynetwork.api.dto.admin.response.AdminResponseDto;

public interface AdminService {
    ResponseDto signup(AdminRequestDto adminRequestDto);

    CommonLoginResponseDto login(CommonLoginRequestDto commonLoginRequestDto);


    AdminResponseDto info(AdminRequestDto adminRequestDto);

    ResponseDto modify(AdminRequestDto adminRequestDto);

    ResponseDto delete(AdminRequestDto adminRequestDto);
}
