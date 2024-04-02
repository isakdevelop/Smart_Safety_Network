package com.smartsafetynetwork.api.service.missingPersonBoard;

import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.domain.MissingPersonBoard;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBWriteRequestDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.missingPerson.MissingPersonRepository;
import com.smartsafetynetwork.api.repository.missingPersonBoard.MissingPersonBoardRepository;
import com.smartsafetynetwork.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissingPersonBoardImpl implements MissingPersonBoardService{
    private final MissingPersonBoardRepository missingPersonBoardRepository;
    private final UserRepository userRepository;
    private final MissingPersonRepository missingPersonRepository;

    @Override
    public ResponseMessage write(MPBWriteRequestDto mpbWriteRequestDto) {
        if (userRepository.findById(mpbWriteRequestDto.getUserId()).isEmpty()) {
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

        return new ResponseMessage(0, "게시글 작성이 완료되었습니다.");
    }
}
