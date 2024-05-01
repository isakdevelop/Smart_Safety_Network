package com.smartsafetynetwork.api.criminal.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.criminal.dto.request.CriminalModifyRequestDto;
import com.smartsafetynetwork.api.criminal.dto.request.CriminalWriteRequestDto;
import com.smartsafetynetwork.api.criminal.dto.response.CriminalDetailResponseDto;
import com.smartsafetynetwork.api.criminal.dto.response.CriminalListResponseDto;
import com.smartsafetynetwork.api.criminal.service.CriminalService;
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
@RequestMapping("/criminal")
public class CriminalController {
    private final CriminalService criminalService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> writeCriminal(@RequestParam String name,
                                                     @RequestParam String age,
                                                     @RequestParam String crime,
                                                     @RequestParam String registrationPlace,
                                                     @RequestParam String address,
                                                     @RequestParam(required = false) MultipartFile image) {
        CriminalWriteRequestDto requestDto = CriminalWriteRequestDto.builder()
                .name(name)
                .age(age)
                .crime(crime)
                .registrationPlace(registrationPlace)
                .address(address)
                .image(image)
                .build();

        return ResponseEntity.ok(criminalService.write(requestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CriminalListResponseDto>> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(criminalService.list(pageable));
    }

    @GetMapping("/list/name")
    public ResponseEntity<Page<CriminalListResponseDto>> listByName(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable,
                                                    @RequestParam("name") String name) {
        return ResponseEntity.ok(criminalService.listByName(pageable, name));
    }

    @GetMapping("/list/crime")
    public ResponseEntity<Page<CriminalListResponseDto>> listByGuilty(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable,
                                                      @RequestParam("crime") String crime) {
        return ResponseEntity.ok(criminalService.listByGuilty(pageable, crime));
    }

    @PostMapping("/detail")
    public ResponseEntity<CriminalDetailResponseDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(criminalService.detail(requestId));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody CriminalModifyRequestDto criminalModifyRequestDto) {
        return ResponseEntity.ok(criminalService.modify(criminalModifyRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(criminalService.delete(requestId));
    }
}
