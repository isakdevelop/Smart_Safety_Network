package com.smartsafetynetwork.api.criminalBoard.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.criminal.repository.CriminalRepository;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardModifyDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardWriteDto;
import com.smartsafetynetwork.api.criminalBoard.model.CriminalBoard;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.criminalBoard.repository.CriminalBoardRepository;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriminalBoardServiceImpl implements CriminalBoardService {
    private final CriminalBoardRepository criminalBoardRepository;
    private final UserRepository userRepository;
    private final CriminalRepository criminalRepository;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto write(CriminalBoardWriteDto writeDto) {
        CriminalBoard cb = CriminalBoard.builder()
                .title(writeDto.getTitle())
                .content(writeDto.getContent())
                .address(writeDto.getAddress())
                .latitude(writeDto.getLatitude())
                .longitude(writeDto.getLongitude())
                .user(userRepository.findById(jwtInfo.getUserIdFromJWT())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "사용자 정보를 찾을 수 없습니다.")))
                .criminal(criminalRepository.findById(writeDto.getCriminalId())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "범죄자 정보를 찾을 수 없습니다.")))
                .build();

        criminalBoardRepository.save(cb);
        return new ResponseDto(HttpStatus.OK.value(), "게시글 작성에 성공하셨습니다");
    }

    @Override
    public ResponseDto modify(CriminalBoardModifyDto modifyDto) {
        CriminalBoard cb = criminalBoardRepository.findById(modifyDto.getCriminalId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "게시글 정보를 찾을 수 없습니다."));

        cb.modify(modifyDto.getTitle(), modifyDto.getContent(), modifyDto.getAddress(),
                modifyDto.getLatitude(), modifyDto.getLongitude());

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
