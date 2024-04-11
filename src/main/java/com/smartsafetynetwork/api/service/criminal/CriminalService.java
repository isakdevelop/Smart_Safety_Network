package com.smartsafetynetwork.api.service.criminal;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.criminal.request.CriminalModifyRequestDto;
import com.smartsafetynetwork.api.dto.criminal.request.CriminalWriteRequestDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalDetailResponseDto;
import com.smartsafetynetwork.api.dto.criminal.response.CriminalListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CriminalService {
    ResponseDto write(CriminalWriteRequestDto criminalWriteRequestDto);

    Page<CriminalListResponseDto> list(Pageable pageable);

    Page<CriminalListResponseDto> listByName(Pageable pageable, String name);

    Page<CriminalListResponseDto> listByGuilty(Pageable pageable, String guilty);

    CriminalDetailResponseDto detail(RequestId requestId);

    ResponseDto modify(CriminalModifyRequestDto criminalModifyRequestDto);

    ResponseDto delete(RequestId requestId);
}
