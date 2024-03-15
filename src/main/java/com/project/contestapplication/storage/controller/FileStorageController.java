package com.project.contestapplication.storage.controller;

import com.project.contestapplication.storage.controller.dto.request.FileDeleteRequestDto;
import com.project.contestapplication.storage.domain.File;
import com.project.contestapplication.storage.domain.FileRequest;
import com.project.contestapplication.storage.domain.FileNameResolver;
import com.project.contestapplication.storage.domain.FileStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/file")
public class FileStorageController {

    private final FileStorage fileStorage;
    private final FileNameResolver fileNameResolver;

    @PostMapping("")
    public ResponseEntity upload( @RequestPart("file") List<MultipartFile> multipartFiles){

        for(MultipartFile multipartFile : multipartFiles){
            String fileName = fileNameResolver.resolve(multipartFile,UUID.randomUUID().toString());
            fileStorage.upload(new FileRequest(multipartFile,fileName));

        }

        return ResponseEntity.ok().body("안녕하세요");
    }

    @DeleteMapping("")
    public ResponseEntity delete(@RequestBody FileDeleteRequestDto dto ){

        fileStorage.delete(dto.getFileName());

        return ResponseEntity.ok().body(dto.getFileName() + "gg");
    }


}
