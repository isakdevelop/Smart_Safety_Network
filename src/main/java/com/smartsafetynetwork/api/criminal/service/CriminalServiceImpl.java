package com.smartsafetynetwork.api.criminal.service;

import com.smartsafetynetwork.api.common.component.FileDelete;
import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.common.component.ImageStorageComponent;
import com.smartsafetynetwork.api.criminal.model.Criminal;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.criminal.dto.CriminalModifyDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalWriteDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.criminal.repository.CriminalRepository;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CriminalServiceImpl implements CriminalService {
    private final CriminalRepository criminalRepository;
    private final ImageStorageComponent imageStorageComponent;
    private final FileDelete fileDelete;

    @Override
    public ResponseDto write(CriminalWriteDto requestDto, MultipartFile image) throws IOException {
        String imagePath = imageStorageComponent.saveImage(image, "criminal/");
        Criminal criminal = Criminal.builder()
                .name(requestDto.getName())
                .age(requestDto.getAge())
                .crime(requestDto.getCrime())
                .registrationPlace(requestDto.getRegistrationPlace())
                .address(requestDto.getAddress())
                .imagePath(imagePath)
                .build();

        criminalRepository.save(criminal);
        return new ResponseDto(HttpStatus.OK.value(), "범죄자 신상 등록에 성공하였습니다.");
    }

    @Override
    public ResponseDto modify(CriminalModifyDto criminalModifyDto, MultipartFile image) throws IOException {
        Criminal criminal = criminalRepository.findById(criminalModifyDto.getCriminal_id())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 범죄자를 찾을 수 없습니다."));

        fileDelete.deleteFile(criminal.getImagePath());

        String imagePath = imageStorageComponent.saveImage(image, "criminal/");

        criminal.modify(
                criminalModifyDto.getName(),
                criminalModifyDto.getRegistrationPlace(),
                criminalModifyDto.getAddress(),
                criminalModifyDto.getCrime(),
                imagePath
        );

        criminalRepository.save(criminal);
        return new ResponseDto(HttpStatus.OK.value(), "수정에 성공하였습니다.");
    }

    @Override
    public ResponseDto delete(RequestId requestId) throws IOException {
        Criminal criminal = criminalRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "해당 범죄자를 찾을 수 없습니다."));

        fileDelete.deleteFile(criminal.getImagePath());
        criminalRepository.deleteById(criminal.getId());
        return new ResponseDto(HttpStatus.OK.value(), "삭제에 성공하였습니다.");
    }
}
