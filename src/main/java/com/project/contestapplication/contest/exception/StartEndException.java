package com.project.contestapplication.contest.exception;

public class StartEndException extends RuntimeException{
    public StartEndException(){
        super("마감일이 시작일보다 빠릅니다");
    }
}
