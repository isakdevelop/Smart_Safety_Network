package com.smartsafetynetwork.api.missingPerson.query;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonDetailDto;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissingPersonQueryService {
    Page<MissingPersonListDto> list(Pageable pageable);

    Page<MissingPersonListDto> listByName(Pageable pageable, String name);

    MissingPersonDetailDto detail(RequestId requestId);
}
