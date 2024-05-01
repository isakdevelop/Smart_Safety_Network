package com.smartsafetynetwork.api.criminalBoard.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.common.component.ValidAdminComponent;
import com.smartsafetynetwork.api.common.component.ValidUserComponent;
import com.smartsafetynetwork.api.criminalBoard.model.CriminalBoard;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.criminalBoard.dto.request.CBRequestDto;
import com.smartsafetynetwork.api.criminalBoard.dto.response.CBResponseDto;
import com.smartsafetynetwork.api.criminalBoard.dto.response.CriminalBoardListDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.criminal.repository.CriminalRepository;
import com.smartsafetynetwork.api.criminalBoard.repository.CriminalBoardRepository;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriminalBoardServiceImpl implements CriminalBoardService {
    private final CriminalBoardRepository criminalBoardRepository;
    private final UserRepository userRepository;
    private final CriminalRepository criminalRepository;
    private final ValidUserComponent validUserComponent;
    private final ValidAdminComponent validAdminComponent;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto write(CBRequestDto cbRequestDto) {
        CriminalBoard cb = CriminalBoard.builder()
                .title(cbRequestDto.getTitle())
                .content(cbRequestDto.getContent())
                .address(cbRequestDto.getAddress())
                .latitude(cbRequestDto.getLatitude())
                .longitude(cbRequestDto.getLongitude())
                .user(userRepository.findById(jwtInfo.getUserIdFromJWT())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "사용자 정보를 찾을 수 없습니다.")))
                .criminal(criminalRepository.findById(cbRequestDto.getCriminalId())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "범죄자 정보를 찾을 수 없습니다.")))
                .build();

        criminalBoardRepository.save(cb);
        return new ResponseDto(HttpStatus.OK.value(), "게시글 작성에 성공하셨습니다");
    }

    @Override
    public Page<CriminalBoardListDto> list(Pageable pageable) {
        if (validUserComponent.isUser(jwtInfo.getUserIdFromJWT())) {
            return criminalBoardRepository.findUserPost(pageable, jwtInfo.getUserIdFromJWT());
        }

        if (validAdminComponent.isAdmin(jwtInfo.getUserIdFromJWT())) {
            return criminalBoardRepository.findAllPost(pageable);
        }

        throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자 정보를 찾을 수 없습니다.");
    }

    @Override
    public Page<CriminalBoardListDto> listByName(Pageable pageable, String name) {
        return criminalBoardRepository.findPostByName(pageable, name);
    }

    @Override
    public CBResponseDto detail(RequestId requestId) {
        return criminalBoardRepository.detail(requestId.getId());
    }

    @Override
    public ResponseDto modify(CBRequestDto cbRequestDto) {
        CriminalBoard cb = criminalBoardRepository.findById(cbRequestDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "게시글 정보를 찾을 수 없습니다."));

        cb.modify(cbRequestDto.getTitle(), cbRequestDto.getContent(), cbRequestDto.getAddress(),
                cbRequestDto.getLatitude(), cbRequestDto.getLongitude());

        criminalBoardRepository.save(cb);
        return new ResponseDto(HttpStatus.OK.value(), "게시글 수정이 완료되었습니다.");
    }

    @Override
    public ResponseDto delete(RequestId requestId) {
        CriminalBoard cb = criminalBoardRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "게시글 정보를 찾을 수 없습니다."));

        criminalBoardRepository.deleteById(cb.getId());
        return new ResponseDto(HttpStatus.OK.value(), "게시글 삭제가 완료되었습니다.");
    }
}
