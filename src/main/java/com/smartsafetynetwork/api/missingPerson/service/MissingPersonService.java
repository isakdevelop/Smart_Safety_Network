package com.smartsafetynetwork.api.missingPerson.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonModifyDto;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonWriteDto;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface MissingPersonService {
    ResponseDto write(MissingPersonWriteDto writeDto, MultipartFile image) throws IOException;

    ResponseDto modify(MissingPersonModifyDto missingPersonModifyDto, MultipartFile image) throws IOException;

    ResponseDto delete(RequestId requestId) throws IOException;
}
