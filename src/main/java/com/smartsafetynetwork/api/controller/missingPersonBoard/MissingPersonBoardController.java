package com.smartsafetynetwork.api.controller.missingPersonBoard;

import com.smartsafetynetwork.api.common.DetailId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBModifyRequestDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBWriteRequestDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBDetailResponseDto;
import com.smartsafetynetwork.api.service.missingPersonBoard.MissingPersonBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mpb")
public class MissingPersonBoardController {
    private final MissingPersonBoardService missingPersonBoardService;

    @PostMapping("/write")
    ResponseMessage write(@RequestBody MPBWriteRequestDto mpbWriteRequestDto) {
        return missingPersonBoardService.write(mpbWriteRequestDto);
    }

    @PatchMapping("/modify")
    ResponseMessage write(@RequestBody MPBModifyRequestDto mpbModifyRequestDto) {
        return missingPersonBoardService.modify(mpbModifyRequestDto);
    }

    @PostMapping("/detail")
    MPBDetailResponseDto detail(@RequestBody DetailId detailId) {
        return missingPersonBoardService.detail(detailId);
    }
}
