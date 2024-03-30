package com.smartsafetynetwork.smartsafetynetwork.controller.user;

import com.smartsafetynetwork.smartsafetynetwork.common.RequestId;
import com.smartsafetynetwork.smartsafetynetwork.common.ResponseMessage;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.request.UserMailRequestDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.request.UserModifyRequestDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.request.UserSignupRequestDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.request.UserLoginRequestDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.response.UserInfoResponseDto;
import com.smartsafetynetwork.smartsafetynetwork.dto.user.response.UserLoginResponseDto;
import com.smartsafetynetwork.smartsafetynetwork.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseMessage signup(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        return userService.signup(userSignupRequestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto signup(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @PostMapping("/info")
    public UserInfoResponseDto info(@RequestBody RequestId requestId) {
        return userService.info(requestId);
    }

    @PostMapping("/modify")
    public ResponseMessage modify(@RequestBody UserModifyRequestDto userModifyRequestDto) {
        return userService.modify(userModifyRequestDto);
    }

    @PostMapping("/mail")
    public ResponseMessage sendMailPassword(@RequestBody UserMailRequestDto userMailRequestDto) {
        return userService.sendMailPassword(userMailRequestDto);
    }

    @DeleteMapping("/delete")
    public ResponseMessage delete(@RequestBody RequestId requestId) {
        return userService.delete(requestId);
    }
}

