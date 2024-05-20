package com.smartsafetynetwork.api.missingPersonBoard.query;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBDetailDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissingPersonBoardQueryService {
    Page<MPBListDto> list(Pageable pageable);

    Page<MPBListDto> listByName(Pageable pageable, String name);

    MPBDetailDto detail(RequestId requestId);
}
