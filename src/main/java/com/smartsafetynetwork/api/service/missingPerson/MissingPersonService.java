package com.smartsafetynetwork.api.service.missingPerson;

import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.missingPerson.request.MissingPersonWriteRequestDto;
import com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonDetailResponseDto;
import com.smartsafetynetwork.api.dto.missingPerson.response.MissingPersonListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissingPersonService {
    ResponseMessage write(MissingPersonWriteRequestDto requestDto);

    Page<MissingPersonListResponseDto> list(Pageable pageable);

    MissingPersonDetailResponseDto detail(RequestId requestId);

    ResponseMessage delete(RequestId requestId);
}
