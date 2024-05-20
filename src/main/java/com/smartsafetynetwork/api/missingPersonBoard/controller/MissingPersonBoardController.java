package com.smartsafetynetwork.api.missingPersonBoard.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBDetailDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBListDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBModifyDto;
import com.smartsafetynetwork.api.missingPersonBoard.dto.MPBWriteDto;
import com.smartsafetynetwork.api.missingPersonBoard.query.MissingPersonBoardQueryService;
import com.smartsafetynetwork.api.missingPersonBoard.service.MissingPersonBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mpb")
public class MissingPersonBoardController {
    private final MissingPersonBoardService missingPersonBoardService;
    private final MissingPersonBoardQueryService missingPersonBoardQueryService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> write(@RequestBody MPBWriteDto mpbWriteDto) {
        return ResponseEntity.ok(missingPersonBoardService.write(mpbWriteDto));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody MPBModifyDto mpbModifyDto) {
        return ResponseEntity.ok(missingPersonBoardService.modify(mpbModifyDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(missingPersonBoardService.delete(requestId));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<MPBListDto>> list(Pageable pageable) {
        return ResponseEntity.ok(missingPersonBoardQueryService.list(pageable));
    }

    @GetMapping("/list/name")
    public ResponseEntity<Page<MPBListDto>> listByName(Pageable pageable, @RequestParam("name") String name) {
        return ResponseEntity.ok(missingPersonBoardQueryService.listByName(pageable, name));
    }

    @PostMapping("/detail")
    public ResponseEntity<MPBDetailDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(missingPersonBoardQueryService.detail(requestId));
    }
}
