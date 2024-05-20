package com.smartsafetynetwork.api.criminalBoard.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardDetailDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardModifyDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardWriteDto;
import com.smartsafetynetwork.api.criminalBoard.dto.CriminalBoardListDto;
import com.smartsafetynetwork.api.criminalBoard.query.CriminalBoardQueryService;
import com.smartsafetynetwork.api.criminalBoard.service.CriminalBoardService;
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
@RequestMapping("/api/cb")
public class CriminalBoardController {
    private final CriminalBoardService criminalBoardService;
    private final CriminalBoardQueryService criminalBoardQueryService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> write(@RequestBody CriminalBoardWriteDto writeDto) {
        return ResponseEntity.ok(criminalBoardService.write(writeDto));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody CriminalBoardModifyDto modifyDto) {
        return ResponseEntity.ok(criminalBoardService.modify(modifyDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(criminalBoardService.delete(requestId));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CriminalBoardListDto>> list(Pageable pageable) {
        return ResponseEntity.ok(criminalBoardQueryService.list(pageable));
    }

    @GetMapping("/list/name")
    public ResponseEntity<Page<CriminalBoardListDto>> listByName(Pageable pageable, @RequestParam("name") String name) {
        return ResponseEntity.ok(criminalBoardQueryService.listByName(pageable, name));
    }

    @PostMapping("/detail")
    public ResponseEntity<CriminalBoardDetailDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(criminalBoardQueryService.detail(requestId));
    }

}
