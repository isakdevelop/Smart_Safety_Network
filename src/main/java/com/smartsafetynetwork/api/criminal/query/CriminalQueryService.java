package com.smartsafetynetwork.api.criminal.query;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.criminal.dto.CriminalDetailDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CriminalQueryService {
    Page<CriminalListDto> list(Pageable pageable);

    Page<CriminalListDto> listByName(Pageable pageable, String name);

    Page<CriminalListDto> listByGuilty(Pageable pageable, String crime);

    CriminalDetailDto detail(RequestId requestId);
}
