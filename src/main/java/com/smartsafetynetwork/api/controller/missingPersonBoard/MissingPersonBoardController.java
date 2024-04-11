package com.smartsafetynetwork.api.controller.missingPersonBoard;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.request.MPBRequestDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBDetailResponseDto;
import com.smartsafetynetwork.api.dto.missingPersonBoard.response.MPBListResponseDto;
import com.smartsafetynetwork.api.service.missingPersonBoard.MissingPersonBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseDto write(@RequestBody MPBRequestDto mpbRequestDto) {
        return missingPersonBoardService.write(mpbRequestDto);
    }

    @GetMapping("/list")
    public Page<MPBListResponseDto> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return missingPersonBoardService.list(pageable);
    }

    @PatchMapping("/modify")
    public ResponseDto modify(@RequestBody MPBRequestDto mpbRequestDto) {
        return missingPersonBoardService.modify(mpbRequestDto);
    }

    @PostMapping("/detail")
    public MPBDetailResponseDto detail(@RequestBody RequestId requestId) {
        return missingPersonBoardService.detail(requestId);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestBody RequestId requestId) {
        return missingPersonBoardService.delete(requestId);
    }
}
