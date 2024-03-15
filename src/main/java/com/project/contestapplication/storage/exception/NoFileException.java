package com.project.contestapplication.storage.exception;

public class NoFileException extends RuntimeException{

    public NoFileException(){
        super("파일이 첨부되지 않았습니다.");
    }
}
