package com.smartsafetynetwork.api.missingPersonBoard.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.request.MPBRequestDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBDetailResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.response.MPBListResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.service.MissingPersonBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDto> write(@RequestBody MPBRequestDto mpbRequestDto) {
        return ResponseEntity.ok(missingPersonBoardService.write(mpbRequestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<MPBListResponseDto>> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(missingPersonBoardService.list(pageable));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody MPBRequestDto mpbRequestDto) {
        return ResponseEntity.ok(missingPersonBoardService.modify(mpbRequestDto));
    }

    @PostMapping("/detail")
    public ResponseEntity<MPBDetailResponseDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(missingPersonBoardService.detail(requestId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(missingPersonBoardService.delete(requestId));
    }
}
