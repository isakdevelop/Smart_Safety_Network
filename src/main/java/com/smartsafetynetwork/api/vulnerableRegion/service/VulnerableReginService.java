package com.smartsafetynetwork.api.vulnerableRegion.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRModifyDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRWriteDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRDetailDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VulnerableReginService {
    ResponseDto write(VRWriteDto vrWriteDto);

    ResponseDto modify(VRModifyDto vrModifyDto);

    ResponseDto delete(RequestId requestId);
}
