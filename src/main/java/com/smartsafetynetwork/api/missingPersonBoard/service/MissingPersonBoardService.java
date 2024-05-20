package com.smartsafetynetwork.api.missingPersonBoard.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBModifyDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBWriteDto;

public interface MissingPersonBoardService {
    ResponseDto write(MPBWriteDto mpbWriteDto);

    ResponseDto modify(MPBModifyDto mpbModifyDto);

    ResponseDto delete(RequestId requestId);
}
