package com.smartsafetynetwork.api.user.controller;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.user.dto.UserSignUpDto;
import com.smartsafetynetwork.api.user.dto.request.UserRequestDto;
import com.smartsafetynetwork.api.user.dto.response.UserInfoDto;
import com.smartsafetynetwork.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody UserSignUpDto userSignUpDto) {
        return ResponseEntity.ok(userService.signup(userSignUpDto));
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> info() {
        return ResponseEntity.ok(userService.info());
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.modify(userRequestDto));
    }

    @PostMapping("/mail")
    public ResponseEntity<ResponseDto> sendMailPassword(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.sendMailPassword(userRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete() {
        return ResponseEntity.ok(userService.delete());
    }
}

