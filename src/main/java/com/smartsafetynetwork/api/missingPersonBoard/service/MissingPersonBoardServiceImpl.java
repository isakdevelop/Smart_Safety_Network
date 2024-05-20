package com.smartsafetynetwork.api.missingPersonBoard.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBModifyDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBWriteDto;
import com.smartsafetynetwork.api.missingPersonBoard.model.MissingPersonBoard;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.missingPerson.repository.MissingPersonRepository;
import com.smartsafetynetwork.api.missingPersonBoard.repository.MissingPersonBoardRepository;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissingPersonBoardServiceImpl implements MissingPersonBoardService{
    private final UserRepository userRepository;
    private final MissingPersonRepository missingPersonRepository;
    private final MissingPersonBoardRepository missingPersonBoardRepository;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto write(MPBWriteDto mpbWriteDto) {
        MissingPersonBoard mpb = MissingPersonBoard.builder()
                .title(mpbWriteDto.getTitle())
                .content(mpbWriteDto.getContent())
                .address(mpbWriteDto.getAddress())
                .latitude(mpbWriteDto.getLatitude())
                .longitude(mpbWriteDto.getLongitude())
                .user(userRepository.findById(jwtInfo.getUserIdFromJWT())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "사용자 정보를 찾을 수 없습니다.")))
                .missingPerson(missingPersonRepository.findById(mpbWriteDto.getMissingPersonId())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "실종자 정보를 찾을 수 없습니다.")))
                .build();

        missingPersonBoardRepository.save(mpb);

        return new ResponseDto(HttpStatus.OK.value(), "게시글 작성이 완료되었습니다.");
    }

    @Override
    public ResponseDto modify(MPBModifyDto mpbModifyDto) {
        MissingPersonBoard mpb = missingPersonBoardRepository.findById(mpbModifyDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        mpb.modify(mpbModifyDto.getTitle(), mpbModifyDto.getContent(), mpbModifyDto.getAddress(),
                mpbModifyDto.getLatitude(), mpbModifyDto.getLongitude());

        missingPersonBoardRepository.save(mpb);

        return new ResponseDto(HttpStatus.OK.value(), "수정에 성공하였습니다.");
    }

    @Override
    public ResponseDto delete(RequestId requestId) {
        MissingPersonBoard mpb = missingPersonBoardRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "게시판 정보를 찾을 수 없습니다."));

        missingPersonBoardRepository.deleteById(mpb.getId());
        return new ResponseDto(HttpStatus.OK.value(), "게시글 삭제에 성공하였습니다.");
    }
}
