package com.smartsafetynetwork.api.criminalBoard.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.criminalBoard.dto.request.CBRequestDto;
import com.smartsafetynetwork.api.criminalBoard.dto.response.CBResponseDto;
import com.smartsafetynetwork.api.criminalBoard.dto.response.CriminalBoardListDto;
import com.smartsafetynetwork.api.criminalBoard.service.CriminalBoardService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cb")
public class CriminalBoardController {
    private final CriminalBoardService criminalBoardService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> write(@RequestBody CBRequestDto cbRequestDto) {
        return ResponseEntity.ok(criminalBoardService.write(cbRequestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CriminalBoardListDto>> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(criminalBoardService.list(pageable));
    }

    @GetMapping("/list/")
    public ResponseEntity<Page<CriminalBoardListDto>> listByName(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable,
                                                 @RequestParam("name") String name) {
        return ResponseEntity.ok(criminalBoardService.listByName(pageable, name));
    }

    @PostMapping("/detail")
    public ResponseEntity<CBResponseDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(criminalBoardService.detail(requestId));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestBody CBRequestDto cbRequestDto) {
        return ResponseEntity.ok(criminalBoardService.modify(cbRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(criminalBoardService.delete(requestId));
    }

}
