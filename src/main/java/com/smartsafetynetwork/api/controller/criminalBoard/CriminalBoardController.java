package com.smartsafetynetwork.api.controller.criminalBoard;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.criminalBoard.request.CBRequestDto;
import com.smartsafetynetwork.api.dto.criminalBoard.response.CBResponseDto;
import com.smartsafetynetwork.api.dto.criminalBoard.response.CriminalBoardListDto;
import com.smartsafetynetwork.api.service.criminalBoard.CriminalBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cb")
public class CriminalBoardController {
    private final CriminalBoardService criminalBoardService;

    @PostMapping("/write")
    ResponseDto write(@RequestBody CBRequestDto cbRequestDto) {
        return criminalBoardService.write(cbRequestDto);
    }

    @GetMapping("/list")
    public Page<CriminalBoardListDto> list(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable) {
        return criminalBoardService.list(pageable);
    }

    @GetMapping("/list/{name}")
    public Page<CriminalBoardListDto> listByName(@PageableDefault(sort = "createAt", direction = Direction.DESC) Pageable pageable,
                                                 @PathVariable String name) {
        return criminalBoardService.listByName(pageable, name);
    }

    @PostMapping("/detail")
    public CBResponseDto detail(@RequestBody RequestId requestId) {
        return criminalBoardService.detail(requestId);
    }

    @PatchMapping("/modify")
    public ResponseDto modify(@RequestBody CBRequestDto cbRequestDto) {
        return criminalBoardService.modify(cbRequestDto);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestBody RequestId requestId) {
        return criminalBoardService.delete(requestId);
    }

}
