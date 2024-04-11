package com.smartsafetynetwork.api.service.user;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.user.request.UserRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.dto.user.response.UserResponseDto;

public interface UserService {
    ResponseDto signup(UserRequestDto userRequestDto);

    CommonLoginResponseDto login(CommonLoginRequestDto commonLoginRequestDto);

    ResponseDto modify(UserRequestDto userRequestDto);

    UserResponseDto info();

    ResponseDto delete();

    ResponseDto sendMailPassword(UserRequestDto userRequestDto);
}
