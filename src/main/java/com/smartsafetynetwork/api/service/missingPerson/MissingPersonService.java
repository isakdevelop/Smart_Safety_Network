package com.smartsafetynetwork.api.service.missingPerson;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.missingPerson.request.MissingPersonWriteRequestDto;
import com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonDetailResponseDto;
import com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonListResponseDto;
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
