package com.smartsafetynetwork.api.service.missingPersonBoard;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBModifyRequestDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBWriteRequestDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBDetailResponseDto;

public interface MissingPersonBoardService {
    ResponseMessage write(MPBWriteRequestDto mpbWriteRequestDto);

    ResponseMessage modify(MPBModifyRequestDto mpbModifyRequestDto);

    MPBDetailResponseDto detail(DetailId detailId);
}
