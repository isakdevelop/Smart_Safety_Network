package com.smartsafetynetwork.api.missingPerson.service;

import com.smartsafetynetwork.api.common.component.FileDelete;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.component.ImageStorageComponent;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonModifyDto;
import com.smartsafetynetwork.api.missingPerson.model.MissingPerson;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonWriteDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.missingPerson.repository.MissingPersonRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MissingPersonServiceImpl implements MissingPersonService{
    private final MissingPersonRepository missingPersonRepository;
    private final ImageStorageComponent imageStorageComponent;
    private final FileDelete fileDelete;

    @Override
    public ResponseDto write(MissingPersonWriteDto writeDto, MultipartFile image) throws IOException {
        String imagePath = imageStorageComponent.saveImage(image, "missingPerson/");
        MissingPerson missingPerson = MissingPerson.builder()
                .name(writeDto.getName())
                .gender(writeDto.getGender())
                .age(writeDto.getAge())
                .location(writeDto.getLocation())
                .date(writeDto.getDate())
                .latitude(writeDto.getLatitude())
                .longitude(writeDto.getLongitude())
                .address(writeDto.getAddress())
                .height(writeDto.getHeight())
                .weight(writeDto.getWeight())
                .physique(writeDto.getPhysique())
                .faceShape(writeDto.getFaceShape())
                .hairColor(writeDto.getHairColor())
                .hairShape(writeDto.getHairShape())
                .cloth(writeDto.getCloth())
                .imagePath(imagePath)
                .build();

        missingPersonRepository.save(missingPerson);
        return new ResponseDto(HttpStatus.OK.value(), "실종자 신상 등록에 성공하였습니다.");
    }

    @Override
    public ResponseDto modify(MissingPersonModifyDto mpd, MultipartFile image) throws IOException {
        MissingPerson ms = missingPersonRepository.findById(mpd.getMissingPersonId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 실종자 입니다."));

        fileDelete.deleteFile(ms.getImagePath());

        String imagePath = imageStorageComponent.saveImage(image, "missingPerson/");

        ms.modify(mpd.getName(), mpd.getGender(), mpd.getAge(), mpd.getLocation(), mpd.getDate(), mpd.getLatitude(),
                mpd.getLongitude(), mpd.getAddress(), mpd.getHeight(), mpd.getWeight(), mpd.getPhysique(),
                mpd.getFaceShape(), mpd.getHairColor(), mpd.getHairShape(), mpd.getCloth(), imagePath);

        missingPersonRepository.save(ms);
        return new ResponseDto(HttpStatus.OK.value(), "수정이 완료되었습니다.");
    }

    @Override
    public ResponseDto delete(RequestId requestId) throws IOException {
        MissingPerson ms = missingPersonRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 실종자 입니다."));

        fileDelete.deleteFile(ms.getImagePath());

        missingPersonRepository.deleteById(ms.getId());
        return new ResponseDto(HttpStatus.OK.value(), "실종자 삭제가 완료되었습니다.");
    }
}
