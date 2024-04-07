package com.smartsafetynetwork.api.service.vulnerableRegin;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.request.VRModifyRequestDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.request.VRWriteRequestDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRDetailResponseDto;
import com.smartsafetynetwork.api.dto.vulnerableRegin.response.VRListResponseDto;

public interface VulnerableReginService {
    ResponseDto write(VRWriteRequestDto vrWriteRequestDto);

    VRListResponseDto list(RequestId requestId);

    ResponseDto modify(VRModifyRequestDto vrModifyRequestDto);

    VRDetailResponseDto detail(DetailId detailId);

    ResponseDto delete(RequestId requestId);
}
