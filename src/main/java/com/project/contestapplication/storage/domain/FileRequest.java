package com.project.contestapplication.storage.domain;

import com.project.contestapplication.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class FileRequest {
    long id;
    private final MultipartFile multipartFile;
    private final String fileName;
    private boolean isUsed = false;
    private User writer;

    public FileRequest(long id, MultipartFile multipartFile, String fileName, boolean isUsed, User writer){
        this.multipartFile = multipartFile;
        this.fileName = fileName;
        this.isUsed =isUsed;
        this.writer = writer;
    }



}
