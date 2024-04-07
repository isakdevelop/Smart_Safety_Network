package com.smartsafetynetwork.api.service.missingPerson;

import com.smartsafetynetwork.api.component.ValidAdminComponent;
import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.component.ImageStorageComponent;
import com.smartsafetynetwork.api.domain.MissingPerson;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.missingPerson.request.MissingPersonWriteRequestDto;
import com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonDetailResponseDto;
import com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonListResponseDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.missingPerson.MissingPersonRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissingPersonServiceImpl implements MissingPersonService{
    private final MissingPersonRepository missingPersonRepository;
    private final ImageStorageComponent imageStorageComponent;
    private final ValidAdminComponent validAdminComponent;

    @Override
    public ResponseDto write(MissingPersonWriteRequestDto requestDto) {
        if (!validAdminComponent.isAdmin(requestDto.getId())) {
            throw new CustomException(Error.FORBIDDEN.getStatus(), "접근 권한이 없습니다.");
        }

        try {
            String imagePath = imageStorageComponent.saveImage(requestDto.getImage());
            MissingPerson missingPerson = MissingPerson.builder()
                    .name(requestDto.getName())
                    .gender(requestDto.getGender())
                    .age(requestDto.getAge())
                    .location(requestDto.getLocation())
                    .date(requestDto.getDate())
                    .latitude(requestDto.getLatitude())
                    .longitude(requestDto.getLongitude())
                    .address(requestDto.getAddress())
                    .height(requestDto.getHeight())
                    .weight(requestDto.getWeight())
                    .physique(requestDto.getPhysique())
                    .faceShape(requestDto.getFaceShape())
                    .hairColor(requestDto.getHairColor())
                    .hairShape(requestDto.getHairShape())
                    .cloth(requestDto.getCloth())
                    .imagePath(imagePath)
                    .build();

            missingPersonRepository.save(missingPerson);
            return new ResponseDto(0, "실종자 신상 등록에 성공하였습니다.");
        } catch (IOException e) {
            throw new CustomException(Error.FORBIDDEN.getStatus(), "이미지 저장 중 오류가 발생했습니다.");
        }
    }

    @Override
    public Page<MissingPersonListResponseDto> list(Pageable pageable) {
        return missingPersonRepository.findAllList(pageable);
    }

    @Override
    public MissingPersonDetailResponseDto detail(RequestId requestId) {
        return missingPersonRepository.findDetailList(requestId.getId());
    }

    @Override
    public ResponseDto delete(RequestId requestId) {
        MissingPerson ms = missingPersonRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 아이디 입니다."));

        missingPersonRepository.deleteById(ms.getId());
        return new ResponseDto(0, "실종자 삭제가 완료되었습니다.");
    }
}
