package com.smartsafetynetwork.api.controller.user;

import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.user.request.UserRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.dto.user.response.UserResponseDto;
import com.smartsafetynetwork.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseDto signup(@RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }

    @PostMapping("/login")
    public CommonLoginResponseDto signup(@RequestBody CommonLoginRequestDto commonLoginRequestDto) {
        return userService.login(commonLoginRequestDto);
    }

    @PostMapping("/info")
    public UserResponseDto info(@RequestBody UserRequestDto userRequestDto) {
        return userService.info(userRequestDto);
    }

    @PatchMapping("/modify")
    public ResponseDto modify(@RequestBody UserRequestDto userRequestDto) {
        return userService.modify(userRequestDto);
    }

    @PostMapping("/mail")
    public ResponseDto sendMailPassword(@RequestBody UserRequestDto userRequestDto) {
        return userService.sendMailPassword(userRequestDto);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestBody UserRequestDto userRequestDto) {
        return userService.delete(userRequestDto);
    }
}

