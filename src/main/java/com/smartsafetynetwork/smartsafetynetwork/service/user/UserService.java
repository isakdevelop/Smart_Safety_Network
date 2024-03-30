package com.smartsafetynetwork.smartsafetynetwork.service.user;

import com.smartsafetynetwork.smartsafetynetwork.common.RequestId;
import com.smartsafetynetwork.smartsafetynetwork.common.ResponseMessage;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.request.UserMailRequestDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.request.UserModifyRequestDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.request.UserSignupRequestDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.request.UserLoginRequestDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.response.UserInfoResponseDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.response.UserLoginResponseDto;

public interface UserService {
    ResponseMessage signup(UserSignupRequestDto userSignupRequestDto);

    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);

    ResponseMessage modify(UserModifyRequestDto userModifyRequestDto);

    UserInfoResponseDto info(RequestId requestId);

    ResponseMessage delete(RequestId requestId);

    ResponseMessage sendMailPassword(UserMailRequestDto userMailRequestDto);
}
