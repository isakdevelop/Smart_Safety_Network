package com.smartsafetynetwork.api.service.missingPersonBoard;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.component.ValidUserComponent;
import com.smartsafetynetwork.api.domain.MissingPersonBoard;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBModifyRequestDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBWriteRequestDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBDetailResponseDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.missingPerson.MissingPersonRepository;
import com.smartsafetynetwork.api.repository.missingPersonBoard.MissingPersonBoardRepository;
import com.smartsafetynetwork.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissingPersonBoardServiceImpl implements MissingPersonBoardService{
    private final UserRepository userRepository;
    private final MissingPersonRepository missingPersonRepository;
    private final MissingPersonBoardRepository missingPersonBoardRepository;
    private final ValidUserComponent validUserComponent;

    @Override
    public ResponseDto write(MPBWriteRequestDto mpbWriteRequestDto) {
        if (validUserComponent.isUser(mpbWriteRequestDto.getUserId())) {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "탈퇴하였거나 없는 유저 입니다.");
        }

        if (missingPersonRepository.findById(mpbWriteRequestDto.getMissingPersonId()).isEmpty()) {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "수사가 완료되었거나 없는 실종자 입니다.");
        }

        MissingPersonBoard mpb = MissingPersonBoard.builder()
                .title(mpbWriteRequestDto.getTitle())
                .content(mpbWriteRequestDto.getContent())
                .address(mpbWriteRequestDto.getAddress())
                .latitude(mpbWriteRequestDto.getLatitude())
                .longitude(mpbWriteRequestDto.getLongitude())
                .user(userRepository.findById(mpbWriteRequestDto.getUserId()).get())
                .missingPerson(missingPersonRepository.findById(mpbWriteRequestDto.getMissingPersonId()).get())
                .build();

        missingPersonBoardRepository.save(mpb);

        return new ResponseDto(0, "게시글 작성이 완료되었습니다.");
    }

    @Override
    public ResponseDto modify(MPBModifyRequestDto mpbModifyRequestDto) {
        if (validUserComponent.isUser(mpbModifyRequestDto.getUserId())) {
            MissingPersonBoard mpb = missingPersonBoardRepository.findById(mpbModifyRequestDto.getId())
                    .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

            mpb.modify(mpbModifyRequestDto.getTitle(), mpbModifyRequestDto.getContent(), mpb.getAddress(),
                    mpbModifyRequestDto.getLatitude(), mpbModifyRequestDto.getLongitude());

            return new ResponseDto(200, "수정에 성공하였습니다.");
        }
        throw new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 유저입니다.");
    }

    @Override
    public MPBDetailResponseDto detail(DetailId detailId) {
        if (validUserComponent.isUser(detailId.getUser_id())) {
            missingPersonBoardRepository.detail(detailId.getWant_id());
        } throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자를 찾을 수 없습니다.");
    }
}
