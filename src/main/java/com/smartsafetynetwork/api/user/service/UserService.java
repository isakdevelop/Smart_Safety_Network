package com.smartsafetynetwork.api.user.service;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.user.dto.request.UserRequestDto;
import com.smartsafetynetwork.api.user.dto.response.UserInfoResponseDto;

public interface UserService {
    ResponseDto signup(UserRequestDto userRequestDto);

    ResponseDto modify(UserRequestDto userRequestDto);

    UserInfoResponseDto info();

    ResponseDto delete();

    ResponseDto sendMailPassword(UserRequestDto userRequestDto);
}
