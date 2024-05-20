package com.smartsafetynetwork.api.user.service;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.user.dto.UserSignUpDto;
import com.smartsafetynetwork.api.user.dto.request.UserRequestDto;
import com.smartsafetynetwork.api.user.dto.response.UserInfoDto;

public interface UserService {
    ResponseDto signup(UserSignUpDto userSignUpDto);

    ResponseDto modify(UserRequestDto userRequestDto);

    UserInfoDto info();

    ResponseDto delete();

    ResponseDto sendMailPassword(UserRequestDto userRequestDto);
}
