package com.smartsafetynetwork.api.vulnerableRegion.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRModifyDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRWriteDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRDetailDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRListDto;
import com.smartsafetynetwork.api.vulnerableRegion.query.VulnerableReginQueryService;
import com.smartsafetynetwork.api.vulnerableRegion.service.VulnerableReginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vr")
@RequiredArgsConstructor
public class VulnerableReginController {
    private final VulnerableReginService vulnerableReginService;
    private final VulnerableReginQueryService vulnerableReginQueryService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> write(@RequestBody VRWriteDto vrWriteDto) {
        return ResponseEntity.ok(vulnerableReginService.write(vrWriteDto));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody VRModifyDto vrModifyDto) {
        return ResponseEntity.ok(vulnerableReginService.modify(vrModifyDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(vulnerableReginService.delete(requestId));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<VRListDto>> list(Pageable pageable) {
        return ResponseEntity.ok(vulnerableReginQueryService.list(pageable));
    }

    @GetMapping("/list/region")
    public ResponseEntity<Page<VRListDto>> listByRegion(Pageable pageable,
                                                        @RequestParam("region") String region) {
        return ResponseEntity.ok(vulnerableReginQueryService.listByRegion(pageable, region));
    }


    @PostMapping("/detail")
    public ResponseEntity<VRDetailDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(vulnerableReginQueryService.detail(requestId));
    }
}
