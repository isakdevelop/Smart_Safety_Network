package com.smartsafetynetwork.api.criminal.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.criminal.dto.request.CriminalModifyRequestDto;
import com.smartsafetynetwork.api.criminal.dto.request.CriminalWriteRequestDto;
import com.smartsafetynetwork.api.criminal.dto.response.CriminalDetailResponseDto;
import com.smartsafetynetwork.api.criminal.dto.response.CriminalListResponseDto;
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
