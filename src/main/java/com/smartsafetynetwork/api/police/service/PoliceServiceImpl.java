package com.smartsafetynetwork.api.police.service;

import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.enums.Role;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.police.dto.PoliceInfoDto;
import com.smartsafetynetwork.api.police.dto.PoliceModifyDto;
import com.smartsafetynetwork.api.police.dto.PoliceAuthorityDto;
import com.smartsafetynetwork.api.police.model.Police;
import com.smartsafetynetwork.api.police.repository.PoliceRepository;
import com.smartsafetynetwork.api.user.model.User;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoliceServiceImpl implements PoliceService {
    private final PoliceRepository policeRepository;
    private final UserRepository userRepository;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto authority(PoliceAuthorityDto policeAuthorityDto) {
        if (policeRepository.existsByPoliceNumber(policeAuthorityDto.getPoliceNumber())) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "이미 등록된 경찰관입니다.");
        }

        User user = userRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND.value(), "해당하는 회원을 찾을 수 없습니다."));

        Police police = Police.builder()
                .policeNumber(policeAuthorityDto.getPoliceNumber())
                .company(policeAuthorityDto.getCompany())
                .department(policeAuthorityDto.getDepartment())
                .user(user)
                .build();

        user.updateRole(Role.ROLE_POLICE);

        userRepository.save(user);
        policeRepository.save(police);

        return new ResponseDto(HttpStatus.OK.value(), "경찰관 권한 등록이 완료되었습니다.");
    }

    @Override
    public PoliceInfoDto info() {
        Police police = policeRepository.findByUser_Id(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND.value(), "해당하는 경찰관을 찾을 수 없습니다."));

        return PoliceInfoDto.builder()
                .policeNumber(police.getPoliceNumber())
                .company(police.getCompany())
                .department(police.getDepartment())
                .status(HttpStatus.OK.value())
                .message("경찰관 정보 조회가 완료되었습니다.")
                .build();
    }

    @Override
    public ResponseDto modify(PoliceModifyDto policeModifyDto) {
        Police police = policeRepository.findByUser_Id(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND.value(), "해당하는 경찰관을 찾을 수 없습니다."));
        police.modify(policeModifyDto.getCompany(), policeModifyDto.getDepartment());
        return new ResponseDto(HttpStatus.OK.value(), "경찰관 정보 수정이 완료되었습니다.");
    }
}
