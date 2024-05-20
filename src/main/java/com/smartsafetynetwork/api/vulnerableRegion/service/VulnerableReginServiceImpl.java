package com.smartsafetynetwork.api.vulnerableRegion.service;

import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.model.VulnerableRegin;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRModifyDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRWriteDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import com.smartsafetynetwork.api.vulnerableRegion.repository.VulnerableReginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VulnerableReginServiceImpl implements VulnerableReginService{
    private final VulnerableReginRepository vulnerableReginRepository;
    private final UserRepository userRepository;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto write(VRWriteDto vrWriteDto) {
        VulnerableRegin vr = VulnerableRegin.builder()
                .title(vrWriteDto.getTitle())
                .content(vrWriteDto.getContent())
                .address(vrWriteDto.getAddress())
                .latitude(vrWriteDto.getLatitude())
                .longitude(vrWriteDto.getLongitude())
                .user(userRepository.findById(jwtInfo.getUserIdFromJWT())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "사용자 정보를 찾을 수 없습니다.")))
                .build();

        vulnerableReginRepository.save(vr);

        return new ResponseDto(HttpStatus.OK.value(), "취약 지역 등록이 완료되었습니다.");
    }

    @Override
    public ResponseDto modify(VRModifyDto vrModifyDto) {
        VulnerableRegin vr = vulnerableReginRepository.findById(vrModifyDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당하는 글이 존재하지 않습니다."));

        vr.modify(vrModifyDto.getTitle(), vrModifyDto.getContent(), vrModifyDto.getAddress(),
                vrModifyDto.getLatitude(), vrModifyDto.getLongitude());

        vulnerableReginRepository.save(vr);
        return new ResponseDto(HttpStatus.OK.value(), "게시글 수정이 완료되었습니다.");
    }

    @Override
    public ResponseDto delete(RequestId requestId) {
        VulnerableRegin vr = vulnerableReginRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 게시글이 존재하지 않습니다."));

        vulnerableReginRepository.deleteById(vr.getId());
        return new ResponseDto(HttpStatus.OK.value(), "게시글 삭제가 완료되었습니다.");
    }

}
