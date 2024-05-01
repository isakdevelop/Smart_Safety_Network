package com.smartsafetynetwork.api.admin.controller;

import com.smartsafetynetwork.api.admin.dto.request.AdminModifyRequestDto;
import com.smartsafetynetwork.api.admin.dto.request.AdminSignUpRequestDto;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.admin.dto.response.AdminInfoResponseDto;
import com.smartsafetynetwork.api.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDto> signup(@RequestBody AdminSignUpRequestDto adminRequestDto) {
        return ResponseEntity.ok(adminService.signup(adminRequestDto));
    }

    @PostMapping("/info")
    public ResponseEntity<AdminInfoResponseDto> info() {
        return ResponseEntity.ok(adminService.info());
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody AdminModifyRequestDto adminRequestDto) {
        return ResponseEntity.ok(adminService.modify(adminRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete() {
        return ResponseEntity.ok(adminService.delete());
    }

}
