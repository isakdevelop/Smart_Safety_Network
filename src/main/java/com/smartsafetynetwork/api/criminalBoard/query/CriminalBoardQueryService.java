package com.smartsafetynetwork.api.criminalBoard.query;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardDetailDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CriminalBoardQueryService {
    Page<CriminalBoardListDto> list(Pageable pageable);

    Page<CriminalBoardListDto> listByName(Pageable pageable, String name);

    CriminalBoardDetailDto detail(RequestId requestId);
}
