package com.smartsafetynetwork.api.service.criminal;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.criminal.request.CriminalModifyRequestDto;
import com.smartsafetynetwork.api.dto.criminal.request.CriminalWriteRequestDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalDetailResponseDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CriminalService {
    ResponseMessage write(CriminalWriteRequestDto criminalWriteRequestDto);

    Page<CriminalListResponseDto> list(Pageable pageable);

    CriminalDetailResponseDto detail(DetailId detailId);

    ResponseMessage modify(CriminalModifyRequestDto criminalModifyRequestDto);

    ResponseMessage delete(DetailId detailId);
}
