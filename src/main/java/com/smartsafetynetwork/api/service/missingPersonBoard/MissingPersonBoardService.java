package com.smartsafetynetwork.api.service.missingPersonBoard;

import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBWriteRequestDto;

public interface MissingPersonBoardService {
    ResponseMessage write(MPBWriteRequestDto mpbWriteRequestDto);
}
