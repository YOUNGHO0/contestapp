package com.project.contestapplication.storage.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UuidFilenameResolver implements FileNameResolver {

    @Override
    public String resolve(MultipartFile multipartFile, String uuid) {
        return multipartFile.getOriginalFilename() + " " + uuid;
    }
}
