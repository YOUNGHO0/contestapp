package com.project.contestapplication.infrastructure.filestorage;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorage {

    boolean upload(File file);

    boolean delete(String key);



}
