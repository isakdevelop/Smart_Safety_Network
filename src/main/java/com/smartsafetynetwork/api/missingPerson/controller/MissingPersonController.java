package com.smartsafetynetwork.api.missingPerson.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.missingPerson.dto.request.MissingPersonWriteRequestDto;
import com.smartsafetynetwork.api.missingPerson.dto.response.MissingPersonDetailResponseDto;
import com.smartsafetynetwork.api.missingPerson.dto.response.MissingPersonListResponseDto;
import com.smartsafetynetwork.api.missingPerson.service.MissingPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mp")
public class MissingPersonController {
    private final MissingPersonService missingPersonService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> write(@RequestParam String name,
                                            @RequestParam String gender,
                                            @RequestParam int age,
                                            @RequestParam String location,
                                            @RequestParam String date,
                                            @RequestParam(required = false) String latitude,
                                            @RequestParam(required = false) String longitude,
                                            @RequestParam String address,
                                            @RequestParam(required = false) Double height,
                                            @RequestParam(required = false) Double weight,
                                            @RequestParam String physique,
                                            @RequestParam String faceShape,
                                            @RequestParam String hairColor,
                                            @RequestParam(required = false) String hairShape,
                                            @RequestParam String cloth,
                                            @RequestParam(required = false) MultipartFile image) {
        MissingPersonWriteRequestDto requestDto = MissingPersonWriteRequestDto.builder()
                .name(name)
                .gender(gender)
                .age(age)
                .location(location)
                .date(date)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .height(height)
                .weight(weight)
                .physique(physique)
                .faceShape(faceShape)
                .hairColor(hairColor)
                .hairShape(hairShape)
                .cloth(cloth)
                .image(image)
                .build();

        return ResponseEntity.ok(missingPersonService.write(requestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<MissingPersonListResponseDto>> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(missingPersonService.list(pageable));
    }

    @GetMapping("/list/name")
    public ResponseEntity<Page<MissingPersonListResponseDto>> listByName(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable,
                                                    @RequestParam("name") String name) {
        return ResponseEntity.ok(missingPersonService.listByName(pageable, name));
    }

    @PostMapping("/detail")
    public ResponseEntity<MissingPersonDetailResponseDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(missingPersonService.detail(requestId));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody MissingPersonWriteRequestDto missingPersonWriteRequestDto) {
        return ResponseEntity.ok(missingPersonService.modify(missingPersonWriteRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(missingPersonService.delete(requestId));
    }
}