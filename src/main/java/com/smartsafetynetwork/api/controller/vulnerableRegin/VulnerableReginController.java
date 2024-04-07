package com.smartsafetynetwork.api.controller.vulnerableRegin;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.request.VRModifyRequestDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.request.VRWriteRequestDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRDetailResponseDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRListResponseDto;
import com.smartsafetynetwork.api.service.vulnerableRegin.VulnerableReginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("/list")
    public VRListResponseDto list(@RequestBody RequestId requestId) {
        return vulnerableReginService.list(requestId);
    }

    @PostMapping("/detail")
    public VRDetailResponseDto detail(@RequestBody DetailId detailId) {
        return vulnerableReginService.detail(detailId);
    }

    @PostMapping("/modify")
    public ResponseDto modify(@RequestBody VRModifyRequestDto vrModifyRequestDto) {
        return vulnerableReginService.modify(vrModifyRequestDto);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestBody RequestId requestId) {
        return vulnerableReginService.delete(requestId);
    }
}
