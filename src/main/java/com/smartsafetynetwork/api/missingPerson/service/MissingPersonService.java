package com.smartsafetynetwork.api.missingPerson.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.missingPerson.dto.request.MissingPersonWriteRequestDto;
import com.smartsafetynetwork.api.missingPerson.dto.response.MissingPersonDetailResponseDto;
import com.smartsafetynetwork.api.missingPerson.dto.response.MissingPersonListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissingPersonService {
    ResponseDto write(MissingPersonWriteRequestDto requestDto);

    Page<MissingPersonListResponseDto> list(Pageable pageable);

    Page<MissingPersonListResponseDto> listByName(Pageable pageable, String name);

    MissingPersonDetailResponseDto detail(RequestId requestId);

    ResponseDto modify(MissingPersonWriteRequestDto missingPersonWriteRequestDto);

    ResponseDto delete(RequestId requestId);
}
