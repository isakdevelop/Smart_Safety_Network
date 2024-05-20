package com.smartsafetynetwork.api.police.service;

import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.police.dto.PoliceInfoDto;
import com.smartsafetynetwork.api.police.dto.PoliceModifyDto;
import com.smartsafetynetwork.api.police.dto.PoliceAuthorityDto;

public interface PoliceService {
    ResponseDto authority(PoliceAuthorityDto policeAuthorityDto);

    PoliceInfoDto info();

    ResponseDto modify(PoliceModifyDto policeModifyDto);
}
