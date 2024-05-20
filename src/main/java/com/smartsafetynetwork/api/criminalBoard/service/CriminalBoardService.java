package com.smartsafetynetwork.api.criminalBoard.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardModifyDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardWriteDto;

public interface CriminalBoardService {
    ResponseDto write(CriminalBoardWriteDto writeDto);

    ResponseDto modify(CriminalBoardModifyDto modifyDto);

    ResponseDto delete(RequestId requestId);
}
