package com.smartsafetynetwork.api.criminalBoard.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.criminalBoard.dto.request.CBRequestDto;
import com.smartsafetynetwork.api.criminalBoard.dto.response.CBResponseDto;
import com.smartsafetynetwork.api.criminalBoard.dto.response.CriminalBoardListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CriminalBoardService {
    ResponseDto write(CBRequestDto cbRequestDto);

    Page<CriminalBoardListDto> list(Pageable pageable);

    Page<CriminalBoardListDto> listByName(Pageable pageable, String name);

    CBResponseDto detail(RequestId requestId);

    ResponseDto modify(CBRequestDto cbRequestDto);

    ResponseDto delete(RequestId requestId);
}
