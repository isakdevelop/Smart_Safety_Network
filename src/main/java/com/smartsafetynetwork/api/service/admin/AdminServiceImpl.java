package com.smartsafetynetwork.api.service.admin;

import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.domain.Admin;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.admin.request.AdminLoginRequestDto;
import com.smartsafetynetwork.api.dto.admin.request.AdminModifyRequestDto;
import com.smartsafetynetwork.api.dto.admin.request.AdminSignupRequestDto;
import com.smartsafetynetwork.api.dto.admin.response.AdminInfoResponseDto;
import com.smartsafetynetwork.api.dto.admin.response.AdminLoginResponseDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseMessage signup(AdminSignupRequestDto adminSignupRequestDto) {
        Admin admin = Admin.builder()
                .policeNumber(adminSignupRequestDto.getPoliceNumber())
                .password(adminSignupRequestDto.getPassword())
                .name(adminSignupRequestDto.getName())
                .phone(adminSignupRequestDto.getPhone())
                .company(adminSignupRequestDto.getCompany())
                .department(adminSignupRequestDto.getDepartment())
                .build();

        if (adminRepository.findByPoliceNumber(adminSignupRequestDto.getPoliceNumber()).isEmpty()) {
            adminRepository.save(admin);
            return new ResponseMessage(0, "회원 가입에 성공하셨습니다.");
        } else {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 경찰 번호 입니다.");
        }
    }

    @Override
    public AdminLoginResponseDto login(AdminLoginRequestDto adminLoginRequestDto) {
        Admin admin = adminRepository.findByPoliceNumber(adminLoginRequestDto.getPoliceNumber())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 경찰 번호 입니다."));

        if (passwordEncoder.matches(adminLoginRequestDto.getPassword(), admin.getPassword())) {
            return new AdminLoginResponseDto(admin.getId(), admin.getName());
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "비밀번호가 틀렸습니다!");
        }
    }

    @Override
    public AdminInfoResponseDto info(RequestId requestId) {
        Admin admin = adminRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        return new AdminInfoResponseDto(admin.getName(), admin.getPhone(), admin.getCompany(), admin.getDepartment());
    }

    @Override
    public ResponseMessage modify(AdminModifyRequestDto adminModifyRequestDto) {
        Admin admin = adminRepository.findById(adminModifyRequestDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));
        admin.modify(adminModifyRequestDto.getName(), adminModifyRequestDto.getPhone(),
                adminModifyRequestDto.getCompany(), adminModifyRequestDto.getDepartment());
        return new ResponseMessage(0, "관리자 정보 변경이 완료되었습니다.");
    }
}
