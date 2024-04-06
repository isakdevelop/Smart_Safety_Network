package com.smartsafetynetwork.api.controller.admin;

import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.admin.request.AdminRequestDto;
import com.smartsafetynetwork.api.dto.admin.response.AdminResponseDto;
import com.smartsafetynetwork.api.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseDto signup(@RequestBody AdminRequestDto adminRequestDto) {
        return adminService.signup(adminRequestDto);
    }

    @PostMapping("/login")
    public CommonLoginResponseDto login(@RequestBody CommonLoginRequestDto commonLoginRequestDto) {
        return adminService.login(commonLoginRequestDto);
    }

    @PostMapping("/info")
    public AdminResponseDto info(@RequestBody AdminRequestDto adminRequestDto) {
        return adminService.info(adminRequestDto);
    }

    @PatchMapping("/modify")
    public ResponseDto modify(@RequestBody AdminRequestDto adminRequestDto) {
        return adminService.modify(adminRequestDto);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestBody AdminRequestDto adminRequestDto) {
        return adminService.delete(adminRequestDto);
    }

}
