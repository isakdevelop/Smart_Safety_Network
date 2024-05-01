package com.smartsafetynetwork.api.missingPersonBoard.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.common.component.ValidAdminComponent;
import com.smartsafetynetwork.api.common.component.ValidUserComponent;
import com.smartsafetynetwork.api.missingPersonBoard.model.MissingPersonBoard;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.missingPersonBoard.dto.request.MPBRequestDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBDetailResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBListResponseDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.missingPerson.repository.MissingPersonRepository;
import com.smartsafetynetwork.api.missingPersonBoard.repository.MissingPersonBoardRepository;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissingPersonBoardServiceImpl implements MissingPersonBoardService{
    private final UserRepository userRepository;
    private final MissingPersonRepository missingPersonRepository;
    private final MissingPersonBoardRepository missingPersonBoardRepository;
    private final ValidUserComponent validUserComponent;
    private final ValidAdminComponent validAdminComponent;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto write(MPBRequestDto mpbRequestDto) {
        MissingPersonBoard mpb = MissingPersonBoard.builder()
                .title(mpbRequestDto.getTitle())
                .content(mpbRequestDto.getContent())
                .address(mpbRequestDto.getAddress())
                .latitude(mpbRequestDto.getLatitude())
                .longitude(mpbRequestDto.getLongitude())
                .user(userRepository.findById(jwtInfo.getUserIdFromJWT())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "사용자 정보를 찾을 수 없습니다.")))
                .missingPerson(missingPersonRepository.findById(mpbRequestDto.getMissingPersonId())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "실종자 정보를 찾을 수 없습니다.")))
                .build();

        missingPersonBoardRepository.save(mpb);

        return new ResponseDto(HttpStatus.OK.value(), "게시글 작성이 완료되었습니다.");
    }

    @Override
    public Page<MPBListResponseDto> list(Pageable pageable) {
        if (validUserComponent.isUser(jwtInfo.getUserIdFromJWT())) {
            return missingPersonBoardRepository.findUserList(pageable, jwtInfo.getUserIdFromJWT());
        }

        if (validAdminComponent.isAdmin(jwtInfo.getUserIdFromJWT())) {
            return missingPersonBoardRepository.findAllList(pageable);
        }

        throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자 정보를 찾을 수 없습니다.");
    }

    @Override
    public ResponseDto modify(MPBRequestDto mpbRequestDto) {
        if (validUserComponent.isUser(mpbRequestDto.getUserId())) {
            MissingPersonBoard mpb = missingPersonBoardRepository.findById(mpbRequestDto.getMissingPersonId())
                    .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

            mpb.modify(mpbRequestDto.getTitle(), mpbRequestDto.getContent(), mpb.getAddress(),
                    mpbRequestDto.getLatitude(), mpbRequestDto.getLongitude());

            return new ResponseDto(HttpStatus.OK.value(), "수정에 성공하였습니다.");
        }
        throw new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 유저입니다.");
    }

    @Override
    public MPBDetailResponseDto detail(RequestId requestId) {
        return missingPersonBoardRepository.detail(requestId.getId());
    }

    @Override
    public ResponseDto delete(RequestId requestId) {
        MissingPersonBoard mpb = missingPersonBoardRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "게시판 정보를 찾을 수 없습니다."));

        missingPersonBoardRepository.deleteById(mpb.getId());
        return new ResponseDto(HttpStatus.OK.value(), "게시글 삭제에 성공하였습니다.");
    }
}
