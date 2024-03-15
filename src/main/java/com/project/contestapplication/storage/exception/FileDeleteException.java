package com.project.contestapplication.storage.exception;

public class FileDeleteException extends RuntimeException{

    public FileDeleteException(){
        super("파일 삭제에 실패하였습니다");
    }
}
