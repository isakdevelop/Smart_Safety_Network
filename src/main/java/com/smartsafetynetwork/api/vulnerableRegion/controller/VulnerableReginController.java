package com.smartsafetynetwork.api.vulnerableRegion.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.request.VRModifyRequestDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.request.VRWriteRequestDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRDetailResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRListResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.service.VulnerableReginService;
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

@RestController
@RequestMapping("/vr")
@RequiredArgsConstructor
public class VulnerableReginController {
    private final VulnerableReginService vulnerableReginService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> write(@RequestBody VRWriteRequestDto vrWriteRequestDto) {
        return ResponseEntity.ok(vulnerableReginService.write(vrWriteRequestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<VRListResponseDto>> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(vulnerableReginService.list(pageable));
    }

    @GetMapping("/list/")
    public ResponseEntity<Page<VRListResponseDto>> listByRegion(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable,
                                        @RequestParam("region") String region) {
        return ResponseEntity.ok(vulnerableReginService.listByRegion(pageable, region));
    }


    @PostMapping("/detail")
    public ResponseEntity<VRDetailResponseDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(vulnerableReginService.detail(requestId));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody VRModifyRequestDto vrModifyRequestDto) {
        return ResponseEntity.ok(vulnerableReginService.modify(vrModifyRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(vulnerableReginService.delete(requestId));
    }
}
