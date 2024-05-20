package com.smartsafetynetwork.api.vulnerableRegion.query;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRDetailDto;
import com.smartsafetynetwork.api.vulnerableRegion.dto.VRListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VulnerableReginQueryService {
    Page<VRListDto> list(Pageable pageable);

    Page<VRListDto> listByRegion(Pageable pageable, String region);

    VRDetailDto detail(RequestId requestId);
}
