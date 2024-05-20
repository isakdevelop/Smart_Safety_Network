package com.smartsafetynetwork.api.criminal.service;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalModifyDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalWriteDto;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface CriminalService {
    ResponseDto write(CriminalWriteDto criminalWriteDto, MultipartFile image) throws IOException;

    ResponseDto modify(CriminalModifyDto criminalModifyDto, MultipartFile image) throws IOException;

    ResponseDto delete(RequestId requestId) throws IOException;
}
