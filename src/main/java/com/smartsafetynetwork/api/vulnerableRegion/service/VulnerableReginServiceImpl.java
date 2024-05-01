package com.smartsafetynetwork.api.vulnerableRegion.service;

import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.common.component.ValidAdminComponent;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.component.ValidUserComponent;
import com.smartsafetynetwork.api.vulnerableRegion.model.VulnerableRegin;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.vulnerableRegion.dto.request.VRModifyRequestDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.request.VRWriteRequestDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRDetailResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRListResponseDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import com.smartsafetynetwork.api.vulnerableRegion.repository.VulnerableReginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VulnerableReginServiceImpl implements VulnerableReginService{
    private final VulnerableReginRepository vulnerableReginRepository;
    private final UserRepository userRepository;
    private final ValidUserComponent validUserComponent;
    private final ValidAdminComponent validAdminComponent;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto write(VRWriteRequestDto vrWriteRequestDto) {
        VulnerableRegin vr = VulnerableRegin.builder()
                .title(vrWriteRequestDto.getTitle())
                .content(vrWriteRequestDto.getContent())
                .address(vrWriteRequestDto.getAddress())
                .latitude(vrWriteRequestDto.getLatitude())
                .longitude(vrWriteRequestDto.getLongitude())
                .user(userRepository.findById(jwtInfo.getUserIdFromJWT())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "사용자 정보를 찾을 수 없습니다.")))
                .build();

        vulnerableReginRepository.save(vr);

        return new ResponseDto(HttpStatus.OK.value(), "취약 지역 등록이 완료되었습니다.");
    }

    @Override
    public Page<VRListResponseDto> list(Pageable pageable) {
        if (validUserComponent.isUser(jwtInfo.getUserIdFromJWT())) {
            return vulnerableReginRepository.findUserPost(pageable, jwtInfo.getUserIdFromJWT());
        }

        if (validAdminComponent.isAdmin(jwtInfo.getUserIdFromJWT())) {
            return vulnerableReginRepository.findAllPost(pageable);
        }

        throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자에 대한 정보를 찾을 수 없습니다.");
    }

    @Override
    public Page<VRListResponseDto> listByRegion(Pageable pageable, String region) {
        if (validUserComponent.isUser(jwtInfo.getUserIdFromJWT())) {
            return vulnerableReginRepository.findUserPostByRegion(pageable, region, jwtInfo.getUserIdFromJWT());
        }

        if (validAdminComponent.isAdmin(jwtInfo.getUserIdFromJWT())) {
            return vulnerableReginRepository.findAllPostByRegion(pageable, region);
        }

        throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자에 대한 정보를 찾을 수 없습니다.");
    }

    @Override
    public VRDetailResponseDto detail(RequestId requestId) {
        return vulnerableReginRepository.findUserDetailPost(requestId.getId());
    }

    @Override
    public ResponseDto modify(VRModifyRequestDto vrModifyRequestDto) {
        VulnerableRegin vr = vulnerableReginRepository.findById(vrModifyRequestDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당하는 글이 존재하지 않습니다."));

        vr.modify(vrModifyRequestDto.getTitle(), vrModifyRequestDto.getContent(), vrModifyRequestDto.getAddress(),
                vrModifyRequestDto.getLatitude(), vrModifyRequestDto.getLongitude());

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
