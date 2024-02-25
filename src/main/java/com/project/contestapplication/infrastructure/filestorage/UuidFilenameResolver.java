package com.project.contestapplication.infrastructure.filestorage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
public class UuidFilenameResolver implements FileNameResolver{

    @Override
    public String resolve(MultipartFile multipartFile, String uuid) {
        return multipartFile.getOriginalFilename() + " " + uuid;
    }
}
