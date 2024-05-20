package com.smartsafetynetwork.api.criminal.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalModifyDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalWriteDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalDetailDto;
import com.smartsafetynetwork.api.criminal.dto.CriminalListDto;
import com.smartsafetynetwork.api.criminal.query.CriminalQueryService;
import com.smartsafetynetwork.api.criminal.service.CriminalService;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/criminal")
public class CriminalController {
    private final CriminalService criminalService;
    private final CriminalQueryService criminalQueryService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> writeCriminal(@RequestPart CriminalWriteDto criminalWriteDto,
                                                     @RequestPart(value = "image", required = false) MultipartFile image)
            throws IOException {

        return ResponseEntity.ok(criminalService.write(criminalWriteDto, image));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestPart CriminalModifyDto criminalModifyDto,
                                              @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.ok(criminalService.modify(criminalModifyDto, image));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) throws IOException {
        return ResponseEntity.ok(criminalService.delete(requestId));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CriminalListDto>> list(Pageable pageable) {
        return ResponseEntity.ok(criminalQueryService.list(pageable));
    }

    @GetMapping("/list/name")
    public ResponseEntity<Page<CriminalListDto>> listByName(Pageable pageable, @RequestParam("name") String name) {
        return ResponseEntity.ok(criminalQueryService.listByName(pageable, name));
    }

    @GetMapping("/list/crime")
    public ResponseEntity<Page<CriminalListDto>> listByGuilty(Pageable pageable, @RequestParam("crime") String crime) {
        return ResponseEntity.ok(criminalQueryService.listByGuilty(pageable, crime));
    }

    @PostMapping("/detail")
    public ResponseEntity<CriminalDetailDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(criminalQueryService.detail(requestId));
    }
}
