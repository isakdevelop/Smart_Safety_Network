package com.smartsafetynetwork.api.service.criminal;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.component.ValidAdminComponent;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.component.ImageStorageComponent;
import com.smartsafetynetwork.api.component.ValidUserComponent;
import com.smartsafetynetwork.api.domain.Criminal;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.criminal.request.CriminalModifyRequestDto;
import com.smartsafetynetwork.api.dto.criminal.request.CriminalWriteRequestDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalDetailResponseDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalListResponseDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.criminal.CriminalRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriminalServiceImpl implements CriminalService {
    private final CriminalRepository criminalRepository;
    private final ImageStorageComponent imageStorageComponent;
    private final ValidAdminComponent validAdminComponent;
    private final ValidUserComponent validUserComponent;

    @Override
    public ResponseDto write(CriminalWriteRequestDto requestDto) {
        if (!validAdminComponent.isAdmin(requestDto.getId())) {
            throw new CustomException(Error.FORBIDDEN.getStatus(), "접근 권한이 없습니다.");
        }

        try {
            String imagePath = imageStorageComponent.saveImage(requestDto.getImage());
            Criminal criminal = Criminal.builder()
                    .name(requestDto.getName())
                    .age(requestDto.getAge())
                    .crime(requestDto.getCrime())
                    .registration_place(requestDto.getRegistrationPlace())
                    .address(requestDto.getAddress())
                    .imagePath(imagePath)
                    .build();

            criminalRepository.save(criminal);
            return new ResponseDto(200, "범죄자 신상 등록에 성공하였습니다.");
        } catch (IOException e) {
            throw new CustomException(Error.FORBIDDEN.getStatus(), "이미지 저장 중 오류가 발생했습니다.");
        }
    }

    @Override
    public Page<CriminalListResponseDto> list(Pageable pageable) {
        return criminalRepository.findAllList(pageable);
    }

    @Override
    public CriminalDetailResponseDto detail(DetailId detailId) {
        if (validUserComponent.isUser(detailId.getUser_id())) {
            return criminalRepository.findDetailList(detailId.getWant_id());
        } throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자를 찾을 수 없습니다.");
    }

    @Override
    public ResponseDto modify(CriminalModifyRequestDto criminalModifyRequestDto) {
        if (validUserComponent.isUser(criminalModifyRequestDto.getUser_id())) {
            Criminal criminal = criminalRepository.findById(criminalModifyRequestDto.getCriminal_id())
                    .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 범죄자를 찾을 수 없습니다."));

            criminal.modify(criminalModifyRequestDto.getAfterName(),
                    criminalModifyRequestDto.getAfterRegistrationPlace(),
                    criminalModifyRequestDto.getAfterAddress(), criminalModifyRequestDto.getAfterCrime());
            return new ResponseDto(200, "수정에 성공하였습니다.");
        } throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자를 찾을 수 없습니다.");
    }

    @Override
    public ResponseDto delete(DetailId detailId) {
        if (validUserComponent.isUser(detailId.getUser_id())) {
            Criminal criminal = criminalRepository.findById(detailId.getWant_id())
                    .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 범죄자를 찾을 수 없습니다."));

            criminalRepository.deleteById(criminal.getId());
            return new ResponseDto(0, "삭제에 성공하였습니다.");
        } throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자를 찾을 수 없습니다.");
    }
}
