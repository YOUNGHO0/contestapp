package com.project.contestapplication.infrastructure.filestorage;

import org.springframework.web.multipart.MultipartFile;

public interface FileNameResolver {

    String resolve(MultipartFile multipartFile,String uuid);
}
