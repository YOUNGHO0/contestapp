package com.project.contestapplication.contest.exception;

public class NoContentException extends RuntimeException{
    public NoContentException(){
        super("내용이 없습니다");
    }
}
