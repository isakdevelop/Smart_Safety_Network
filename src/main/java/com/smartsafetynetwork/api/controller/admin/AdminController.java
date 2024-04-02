package com.smartsafetynetwork.api.controller.admin;

import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.admin.request.AdminLoginRequestDto;
import com.smartsafetynetwork.api.dto.admin.request.AdminModifyRequestDto;
import com.smartsafetynetwork.api.dto.admin.request.AdminSignupRequestDto;
import com.smartsafetynetwork.api.dto.admin.response.AdminInfoResponseDto;
import com.smartsafetynetwork.api.dto.admin.response.AdminLoginResponseDto;
import com.smartsafetynetwork.api.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/signup")
    public ResponseMessage signup(@RequestBody AdminSignupRequestDto adminSignupRequestDto) {
        return adminService.signup(adminSignupRequestDto);
    }

    @PostMapping("/login")
    public AdminLoginResponseDto login(@RequestBody AdminLoginRequestDto adminLoginRequestDto) {
        return adminService.login(adminLoginRequestDto);
    }

    @PostMapping("/info")
    public AdminInfoResponseDto info(@RequestBody RequestId requestId) {
        return adminService.info(requestId);
    }

    @PostMapping("/modify")
    public ResponseMessage modify(@RequestBody AdminModifyRequestDto adminModifyRequestDto) {
        return adminService.modify(adminModifyRequestDto);
    }

}
