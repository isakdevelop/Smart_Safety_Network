package com.smartsafetynetwork.api.admin.service;

import com.smartsafetynetwork.api.admin.dto.request.AdminModifyRequestDto;
import com.smartsafetynetwork.api.admin.dto.request.AdminSignUpRequestDto;
import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.common.enums.Role;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.admin.model.Admin;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.admin.dto.response.AdminInfoResponseDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto signup(AdminSignUpRequestDto adminRequestDto) {
        Admin admin = Admin.builder()
                .policeNumber(adminRequestDto.getPoliceNumber())
                .password(passwordEncoder.encode(adminRequestDto.getPassword()))
                .name(adminRequestDto.getName())
                .phone(adminRequestDto.getPhone())
                .company(adminRequestDto.getCompany())
                .department(adminRequestDto.getDepartment())
                .role(Role.ROLE_ADMIN)
                .build();

        if (adminRepository.findByPoliceNumber(adminRequestDto.getPoliceNumber()).isEmpty()) {
            adminRepository.save(admin);
            return new ResponseDto(HttpStatus.OK.value(), "회원 가입에 성공하셨습니다.");
        } else {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 경찰 번호 입니다.");
        }
    }

    @Override
    public AdminInfoResponseDto info() {
        Admin admin = adminRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "관리자 정보를 찾을 수 없습니다."));

        return AdminInfoResponseDto.builder()
                .name(admin.getName())
                .phone(admin.getPhone())
                .company(admin.getCompany())
                .department(admin.getDepartment())
                .status(HttpStatus.OK.value())
                .message("관리자 정보를 성공적으로 가져왔습니다.")
                .build();
    }

    @Override
    public ResponseDto modify(AdminModifyRequestDto adminRequestDto) {
        Admin admin = adminRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        admin.modify(adminRequestDto.getName(), adminRequestDto.getPhone(), adminRequestDto.getCompany(),
                adminRequestDto.getDepartment());
        adminRepository.save(admin);

        return new ResponseDto(HttpStatus.OK.value(), "관리자 정보 수정이 완료되었습니다.");
    }

    @Override
    public ResponseDto delete() {
        Admin admin = adminRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        adminRepository.deleteById(admin.getId());
        return new ResponseDto(HttpStatus.OK.value(), "회원 탈퇴가 완료되었습니다.");
    }

}
