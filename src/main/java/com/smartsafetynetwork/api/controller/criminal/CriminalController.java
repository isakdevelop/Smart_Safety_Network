package com.smartsafetynetwork.api.controller.criminal;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.criminal.request.CriminalModifyRequestDto;
import com.smartsafetynetwork.api.dto.criminal.request.CriminalWriteRequestDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalDetailResponseDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalListResponseDto;
import com.smartsafetynetwork.api.service.criminal.CriminalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
    public ResponseMessage writeCriminal(@RequestParam String id,
                                                         @RequestParam String name,
                                                         @RequestParam String age,
                                                         @RequestParam String crime,
                                                         @RequestParam String registrationPlace,
                                                         @RequestParam String address,
                                                         @RequestParam(required = false) MultipartFile image) {
        CriminalWriteRequestDto requestDto = CriminalWriteRequestDto.builder()
                .id(id)
                .name(name)
                .age(age)
                .crime(crime)
                .registrationPlace(registrationPlace)
                .address(address)
                .image(image)
                .build();

        return criminalService.write(requestDto);
    }

    @GetMapping("/list")
    public Page<CriminalListResponseDto> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return criminalService.list(pageable);
    }

    @PostMapping("/detail")
    public CriminalDetailResponseDto detail(@RequestBody DetailId detailId) {
        return criminalService.detail(detailId);
    }

    @PatchMapping("/modify")
    public ResponseMessage modify(@RequestBody CriminalModifyRequestDto criminalModifyRequestDto) {
        return criminalService.modify(criminalModifyRequestDto);
    }

    @DeleteMapping("/delete")
    public ResponseMessage delete(@RequestBody DetailId detailId) {
        return criminalService.delete(detailId);
    }
}
