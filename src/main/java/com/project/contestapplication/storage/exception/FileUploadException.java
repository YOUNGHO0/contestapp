package com.project.contestapplication.storage.exception;

public class FileUploadException extends RuntimeException {
    public FileUploadException(){
        super("파일 업로드중 문제가 발생했습니다");
    }
}
