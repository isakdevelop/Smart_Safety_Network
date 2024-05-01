package com.smartsafetynetwork.api.vulnerableRegion.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.request.VRModifyRequestDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.request.VRWriteRequestDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRDetailResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.response.VRListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VulnerableReginService {
    ResponseDto write(VRWriteRequestDto vrWriteRequestDto);

    Page<VRListResponseDto> list(Pageable pageable);

    Page<VRListResponseDto> listByRegion(Pageable pageable, String region);

    ResponseDto modify(VRModifyRequestDto vrModifyRequestDto);

    VRDetailResponseDto detail(RequestId requestId);

    ResponseDto delete(RequestId requestId);
}
