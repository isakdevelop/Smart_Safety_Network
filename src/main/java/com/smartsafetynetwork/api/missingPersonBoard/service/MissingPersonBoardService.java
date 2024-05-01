package com.smartsafetynetwork.api.missingPersonBoard.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.request.MPBRequestDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBDetailResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissingPersonBoardService {
    ResponseDto write(MPBRequestDto mpbRequestDto);
    Page<MPBListResponseDto> list(Pageable pageable);

    ResponseDto modify(MPBRequestDto mpbRequestDto);

    MPBDetailResponseDto detail(RequestId requestId);

    ResponseDto delete(RequestId requestId);
}
