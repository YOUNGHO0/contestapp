package com.project.contestapplication.infrastructure.filestorage;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FakeMultipartfile implements MultipartFile {

    String originalFileName;
    String name;

    FakeMultipartfile(String originalFileName, String name){
        this.originalFileName = originalFileName;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOriginalFilename() {
        return this.originalFileName;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return new byte[0];
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {

    }
}
