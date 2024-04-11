package com.smartsafetynetwork.api.controller.vulnerableRegin;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.request.VRModifyRequestDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.request.VRWriteRequestDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRDetailResponseDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRListResponseDto;
import com.smartsafetynetwork.api.service.vulnerableRegin.VulnerableReginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vr")
@RequiredArgsConstructor
public class VulnerableReginController {
    private final VulnerableReginService vulnerableReginService;

    @PostMapping("/write")
    public ResponseDto write(@RequestBody VRWriteRequestDto vrWriteRequestDto) {
        return vulnerableReginService.write(vrWriteRequestDto);
    }

    @GetMapping("/list")
    public Page<VRListResponseDto> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return vulnerableReginService.list(pageable);
    }

    @GetMapping("/list/{region}")
    public Page<VRListResponseDto> listByRegion(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable,
                                        @PathVariable String region) {
        return vulnerableReginService.listByRegion(pageable, region);
    }


    @PostMapping("/detail")
    public VRDetailResponseDto detail(@RequestBody RequestId requestId) {
        return vulnerableReginService.detail(requestId);
    }

    @PatchMapping("/modify")
    public ResponseDto modify(@RequestBody VRModifyRequestDto vrModifyRequestDto) {
        return vulnerableReginService.modify(vrModifyRequestDto);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestBody RequestId requestId) {
        return vulnerableReginService.delete(requestId);
    }
}
