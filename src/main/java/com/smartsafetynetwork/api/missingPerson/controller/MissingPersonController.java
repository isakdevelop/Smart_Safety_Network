package com.smartsafetynetwork.api.missingPerson.controller;

import com.smartsafetynetwork.api.common.dto.RequestId;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonListDto;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonModifyDto;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonWriteDto;
import com.smartsafetynetwork.api.missingPerson.dto.MissingPersonDetailDto;
import com.smartsafetynetwork.api.missingPerson.query.MissingPersonQueryService;
import com.smartsafetynetwork.api.missingPerson.service.MissingPersonService;
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
@RequestMapping("/api/mp")
public class MissingPersonController {
    private final MissingPersonService missingPersonService;
    private final MissingPersonQueryService missingPersonQueryService;

    @PostMapping("/write")
    public ResponseEntity<ResponseDto> write(@RequestPart("missingPersonWriteDto") MissingPersonWriteDto missingPersonWriteDto,
                                             @RequestPart(value = "image", required = false) MultipartFile image)
            throws IOException {
        return ResponseEntity.ok(missingPersonService.write(missingPersonWriteDto, image));
    }

    @PatchMapping("/modify")
    public ResponseEntity<ResponseDto> modify(@RequestPart MissingPersonModifyDto missingPersonModifyDto,
                                              @RequestPart(value = "image", required = false) MultipartFile image)
            throws IOException {
        return ResponseEntity.ok(missingPersonService.modify(missingPersonModifyDto, image));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestBody RequestId requestId) throws IOException {
        return ResponseEntity.ok(missingPersonService.delete(requestId));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<MissingPersonListDto>> list(Pageable pageable) {
        return ResponseEntity.ok(missingPersonQueryService.list(pageable));
    }

    @GetMapping("/list/name")
    public ResponseEntity<Page<MissingPersonListDto>> listByName(Pageable pageable, @RequestParam("name") String name) {
        return ResponseEntity.ok(missingPersonQueryService.listByName(pageable, name));
    }

    @PostMapping("/detail")
    public ResponseEntity<MissingPersonDetailDto> detail(@RequestBody RequestId requestId) {
        return ResponseEntity.ok(missingPersonQueryService.detail(requestId));
    }
}
