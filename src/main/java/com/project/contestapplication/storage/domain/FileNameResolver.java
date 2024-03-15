package com.project.contestapplication.storage.domain;

import org.springframework.web.multipart.MultipartFile;

public interface FileNameResolver {

    String resolve(MultipartFile multipartFile,String uuid);
}
