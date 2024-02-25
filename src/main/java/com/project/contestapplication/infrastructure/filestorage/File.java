package com.project.contestapplication.infrastructure.filestorage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class File {
    private final MultipartFile multipartFile;
    private final String fileName;



}
