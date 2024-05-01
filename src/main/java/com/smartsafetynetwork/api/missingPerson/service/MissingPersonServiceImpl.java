package com.smartsafetynetwork.api.missingPerson.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.component.ImageStorageComponent;
import com.smartsafetynetwork.api.missingPerson.model.MissingPerson;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.missingPerson.dto.request.MissingPersonWriteRequestDto;
import com.smartsafetynetwork.api.missingPerson.dto.response.MissingPersonDetailResponseDto;
import com.smartsafetynetwork.api.missingPerson.dto.response.MissingPersonListResponseDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.missingPerson.repository.MissingPersonRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissingPersonServiceImpl implements MissingPersonService{
    private final MissingPersonRepository missingPersonRepository;
    private final ImageStorageComponent imageStorageComponent;

    @Override
    public ResponseDto write(MissingPersonWriteRequestDto requestDto) {
        try {
            String imagePath = imageStorageComponent.saveImage(requestDto.getImage(), "missingPerson/");
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
            return new ResponseDto(HttpStatus.OK.value(), "실종자 신상 등록에 성공하였습니다.");
        } catch (IOException e) {
            throw new CustomException(Error.FORBIDDEN.getStatus(), "이미지 저장 중 오류가 발생했습니다.");
        }
    }

    @Override
    public Page<MissingPersonListResponseDto> list(Pageable pageable) {
        return missingPersonRepository.findAllList(pageable);
    }

    @Override
    public Page<MissingPersonListResponseDto> listByName(Pageable pageable, String name) {
        return missingPersonRepository.findListByName(pageable, name);
    }

    @Override
    public MissingPersonDetailResponseDto detail(RequestId requestId) {
        return missingPersonRepository.findDetailList(requestId.getId());
    }

    @Override
    public ResponseDto modify(MissingPersonWriteRequestDto mpd) {
        MissingPerson ms = missingPersonRepository.findById(mpd.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 실종자 입니다."));

        try {
            String imagePath = imageStorageComponent.saveImage(mpd.getImage(), "missingPerson/");

            ms.modify(mpd.getName(), mpd.getGender(), mpd.getAge(), mpd.getLocation(), mpd.getDate(), mpd.getLatitude(),
                    mpd.getLongitude(), mpd.getAddress(), mpd.getHeight(), mpd.getWeight(), mpd.getPhysique(),
                    mpd.getFaceShape(), mpd.getHairColor(), mpd.getHairShape(), mpd.getCloth(), imagePath);

            missingPersonRepository.save(ms);
            return new ResponseDto(HttpStatus.OK.value(), "수정이 완료되었습니다.");
        } catch (IOException e) {
            throw new CustomException(Error.FORBIDDEN.getStatus(), "이미지 저장 중 오류가 발생했습니다.");
        }
    }

    @Override
    public ResponseDto delete(RequestId requestId) {
        MissingPerson ms = missingPersonRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 실종자 입니다."));

        missingPersonRepository.deleteById(ms.getId());
        return new ResponseDto(HttpStatus.OK.value(), "실종자 삭제가 완료되었습니다.");
    }
}
