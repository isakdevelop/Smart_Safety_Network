package com.smartsafetynetwork.api.service.criminal;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.component.ImageStorageComponent;
import com.smartsafetynetwork.api.domain.Criminal;
import com.smartsafetynetwork.api.enums.Error;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriminalServiceImpl implements CriminalService {
    private final CriminalRepository criminalRepository;
    private final ImageStorageComponent imageStorageComponent;

    @Override
    public ResponseDto write(CriminalWriteRequestDto requestDto) {
        try {
            String imagePath = imageStorageComponent.saveImage(requestDto.getImage(), "criminal/");
            Criminal criminal = Criminal.builder()
                    .name(requestDto.getName())
                    .age(requestDto.getAge())
                    .crime(requestDto.getCrime())
                    .registration_place(requestDto.getRegistrationPlace())
                    .address(requestDto.getAddress())
                    .imagePath(imagePath)
                    .build();

            criminalRepository.save(criminal);
            return new ResponseDto(HttpStatus.OK.value(), "범죄자 신상 등록에 성공하였습니다.");
        } catch (IOException e) {
            throw new CustomException(Error.FORBIDDEN.getStatus(), "이미지 저장 중 오류가 발생했습니다.");
        }
    }

    @Override
    public Page<CriminalListResponseDto> list(Pageable pageable) {
        return criminalRepository.findAllList(pageable);
    }

    @Override
    public Page<CriminalListResponseDto> listByName(Pageable pageable, String name) {
        return criminalRepository.findByNameList(pageable, name);
    }

    @Override
    public Page<CriminalListResponseDto> listByGuilty(Pageable pageable, String guilty) {
        return criminalRepository.findByGuiltyList(pageable, guilty);
    }

    @Override
    public CriminalDetailResponseDto detail(RequestId requestId) {
        return criminalRepository.findDetailList(requestId.getId());
    }

    @Override
    public ResponseDto modify(CriminalModifyRequestDto criminalModifyRequestDto) {
        Criminal criminal = criminalRepository.findById(criminalModifyRequestDto.getCriminal_id())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 범죄자를 찾을 수 없습니다."));

        criminal.modify(criminalModifyRequestDto.getName(),
                criminalModifyRequestDto.getRegistrationPlace(),
                criminalModifyRequestDto.getAddress(), criminalModifyRequestDto.getCrime());

        criminalRepository.save(criminal);
        return new ResponseDto(HttpStatus.OK.value(), "수정에 성공하였습니다.");
    }

    @Override
    public ResponseDto delete(RequestId requestId) {
        Criminal criminal = criminalRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 범죄자를 찾을 수 없습니다."));

        criminalRepository.deleteById(criminal.getId());
        return new ResponseDto(HttpStatus.OK.value(), "삭제에 성공하였습니다.");
    }
}
