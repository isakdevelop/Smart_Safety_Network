package com.smartsafetynetwork.api.police.controller;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.police.dto.PoliceInfoDto;
import com.smartsafetynetwork.api.police.dto.PoliceModifyDto;
import com.smartsafetynetwork.api.police.dto.PoliceAuthorityDto;
import com.smartsafetynetwork.api.police.service.PoliceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/police")
public class PoliceController {
    private final PoliceService policeService;

    @PostMapping("/authority")
    public ResponseEntity<ResponseDto> authority(@RequestBody PoliceAuthorityDto policeAuthorityDto) {
        return ResponseEntity.ok(policeService.authority(policeAuthorityDto));
    }

    @PostMapping("/info")
    public ResponseEntity<PoliceInfoDto> info() {
        return ResponseEntity.ok(policeService.info());
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody PoliceModifyDto policeModifyDto) {
        return ResponseEntity.ok(policeService.modify(policeModifyDto));
    }
}
