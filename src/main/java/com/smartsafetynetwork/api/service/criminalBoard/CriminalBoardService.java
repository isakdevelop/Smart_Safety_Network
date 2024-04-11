package com.smartsafetynetwork.api.service.criminalBoard;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.criminalBoard.request.CBRequestDto;
import com.smartsafetynetwork.api.dto.criminalBoard.response.CBResponseDto;
import com.smartsafetynetwork.api.dto.criminalBoard.response.CriminalBoardListDto;
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
