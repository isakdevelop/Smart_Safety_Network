package com.smartsafetynetwork.api.service.admin;

import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.component.JWTProvider;
import com.smartsafetynetwork.api.domain.value.Role;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.domain.Admin;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.admin.request.AdminRequestDto;
import com.smartsafetynetwork.api.dto.admin.response.AdminResponseDto;
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
    private final JWTProvider jwtProvider;

    @Override
    public ResponseDto signup(AdminRequestDto adminRequestDto) {
        Admin admin = Admin.builder()
                .policeNumber(adminRequestDto.getPoliceNumber())
                .password(adminRequestDto.getPassword())
                .name(adminRequestDto.getName())
                .phone(adminRequestDto.getPhone())
                .company(adminRequestDto.getCompany())
                .department(adminRequestDto.getDepartment())
                .role(Role.ROLE_ADMIN)
                .build();

        if (adminRepository.findByPoliceNumber(adminRequestDto.getPoliceNumber()).isEmpty()) {
            adminRepository.save(admin);
            return new ResponseDto(0, "회원 가입에 성공하셨습니다.");
        } else {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 경찰 번호 입니다.");
        }
    }

    @Override
    public CommonLoginResponseDto login(CommonLoginRequestDto commonLoginRequestDto) {
        Admin admin = adminRepository.findByPoliceNumber(commonLoginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 경찰 번호 입니다."));

        if (passwordEncoder.matches(commonLoginRequestDto.getPassword(), admin.getPassword())) {
            String token = jwtProvider.create(admin.getId());
            return new CommonLoginResponseDto(200, "로그인에 성공하셨습니다.", token, 3600, admin.getId());
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "비밀번호가 틀렸습니다!");
        }
    }

    @Override
    public AdminResponseDto info(AdminRequestDto adminRequestDto) {
        Admin admin = adminRepository.findById(adminRequestDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "관리자 정보를 찾을 수 없습니다."));

        return AdminResponseDto.builder()
                .name(admin.getName())
                .phone(admin.getPhone())
                .company(admin.getCompany())
                .department(admin.getDepartment())
                .status(200)
                .message("관리자 정보를 성공적으로 가져왔습니다.")
                .build();
    }

    @Override
    public ResponseDto modify(AdminRequestDto adminRequestDto) {
        Admin admin = adminRepository.findById(adminRequestDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        admin.modify(adminRequestDto.getName(), adminRequestDto.getPhone(), adminRequestDto.getCompany(),
                adminRequestDto.getDepartment());
        adminRepository.save(admin);

        return new ResponseDto(200, "관리자 정보 수정이 완료되었습니다.");
    }

    @Override
    public ResponseDto delete(AdminRequestDto adminRequestDto) {
        Admin admin = adminRepository.findById(adminRequestDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        adminRepository.deleteById(admin.getId());
        return new ResponseDto(200, "회원 탈퇴가 완료되었습니다.");
    }

}
