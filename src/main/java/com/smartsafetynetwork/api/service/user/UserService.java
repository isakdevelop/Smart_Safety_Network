package com.smartsafetynetwork.api.service.user;

import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.user.request.UserMailRequestDto;
import com.smartsafetynetwork.api.dto.user.request.UserModifyRequestDto;
import com.smartsafetynetwork.api.dto.user.request.UserSignupRequestDto;
import com.smartsafetynetwork.api.dto.user.request.UserLoginRequestDto;
import com.smartsafetynetwork.api.dto.user.response.UserInfoResponseDto;
import com.smartsafetynetwork.api.dto.user.response.UserLoginResponseDto;

public interface UserService {
    ResponseMessage signup(UserSignupRequestDto userSignupRequestDto);

    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);

    ResponseMessage modify(UserModifyRequestDto userModifyRequestDto);

    UserInfoResponseDto info(RequestId requestId);

    ResponseMessage delete(RequestId requestId);

    ResponseMessage sendMailPassword(UserMailRequestDto userMailRequestDto);
}
