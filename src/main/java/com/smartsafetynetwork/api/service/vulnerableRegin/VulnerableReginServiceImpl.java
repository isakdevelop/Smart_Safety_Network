package com.smartsafetynetwork.api.service.vulnerableRegin;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.component.ValidAdminComponent;
import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.component.ValidUserComponent;
import com.smartsafetynetwork.api.domain.VulnerableRegin;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.vulnerableRegin.request.VRModifyRequestDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.request.VRWriteRequestDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.PageDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRDetailResponseDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRListResponseDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.user.UserRepository;
import com.smartsafetynetwork.api.repository.vulnerableRegin.VulnerableReginRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VulnerableReginServiceImpl implements VulnerableReginService{
    private final VulnerableReginRepository vulnerableReginRepository;
    private final UserRepository userRepository;
    private final ValidUserComponent validUserComponent;
    private final ValidAdminComponent validAdminComponent;

    @Override
    public ResponseDto write(VRWriteRequestDto vrWriteRequestDto) {
        VulnerableRegin vr = VulnerableRegin.builder()
                .title(vrWriteRequestDto.getTitle())
                .content(vrWriteRequestDto.getContent())
                .address(vrWriteRequestDto.getAddress())
                .latitude(vrWriteRequestDto.getLatitude())
                .longitude(vrWriteRequestDto.getLongitude())
                .user(userRepository.findById(vrWriteRequestDto.getUserId()).get())
                .build();

        vulnerableReginRepository.save(vr);

        return new ResponseDto(0, "취약 지역 등록이 완료되었습니다.");
    }

    @Override
    public VRListResponseDto list(RequestId requestId) {
        if (validAdminComponent.isAdmin(requestId.getId())) {

            List<PageDto> post = vulnerableReginRepository.findAllPost();
            return new VRListResponseDto(post);
        }
        if (userRepository.existsById(requestId.getId())) {
            List<PageDto> userPost = vulnerableReginRepository.findUserPost(requestId.getId());
            return new VRListResponseDto(userPost);
        }
        throw new CustomException(Error.NOT_FOUND.getStatus(), "조건에 해당하는 게시글이 없습니다.");
    }

    @Override
    public VRDetailResponseDto detail(DetailId detailId) {
        if (validUserComponent.isUser(detailId.getUser_id())) {
            VulnerableRegin vr = vulnerableReginRepository.findById(detailId.getWant_id())
                    .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 게시글이 존재하지 않습니다."));

            return vulnerableReginRepository.findMyPost(vr.getId());
        } throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자를 찾을 수 없습니다.");
    }

    @Override
    public ResponseDto modify(VRModifyRequestDto vrModifyRequestDto) {
        if (userRepository.existsById(vrModifyRequestDto.getId())) {
            VulnerableRegin vr = vulnerableReginRepository.findById(vrModifyRequestDto.getId())
                    .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당하는 글이 존재하지 않습니다."));

            vr.modify(vrModifyRequestDto.getTitle(), vrModifyRequestDto.getContent(), vrModifyRequestDto.getAddress(),
                    vrModifyRequestDto.getLatitude(), vrModifyRequestDto.getLongitude());

            return new ResponseDto(0, "게시글 수정이 완료되었습니다.");
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "해당하는 게시글은 존재하지 않습니다.");
        }
    }

    @Override
    public ResponseDto delete(RequestId requestId) {
        VulnerableRegin vr = vulnerableReginRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 게시글이 존재하지 않습니다."));

        vulnerableReginRepository.deleteById(vr.getId());
        return new ResponseDto(0, "게시글 삭제가 완료되었습니다.");
    }

}
