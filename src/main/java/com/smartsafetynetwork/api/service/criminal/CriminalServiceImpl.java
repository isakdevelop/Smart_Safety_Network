package com.smartsafetynetwork.api.service.criminal;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.component.ValidAdminComponent;
import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.component.ImageStorageComponent;
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

    @Override
    public ResponseMessage write(CriminalWriteRequestDto requestDto) {
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
            return new ResponseMessage(0, "범죄자 신상 등록에 성공하였습니다.");
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
        return criminalRepository.findDetailList(detailId.getId());
    }

    @Override
    public ResponseMessage modify(CriminalModifyRequestDto criminalModifyRequestDto) {
        Criminal criminal = criminalRepository.findById(criminalModifyRequestDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당하는 id가 존재하지 않습니다."));

        criminal.modify(criminalModifyRequestDto.getAfterName(), criminalModifyRequestDto.getAfterRegistrationPlace(),
                criminalModifyRequestDto.getAfterAddress(), criminalModifyRequestDto.getAfterCrime());
        return new ResponseMessage(0, "수정에 성공하였습니다.");
    }

    @Override
    public ResponseMessage delete(RequestId requestId) {
        Criminal criminal = criminalRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        criminalRepository.deleteById(criminal.getId());
        return new ResponseMessage(0, "삭제에 성공하였습니다.");
    }
}
