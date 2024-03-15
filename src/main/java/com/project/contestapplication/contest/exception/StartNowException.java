package com.project.contestapplication.contest.exception;

public class StartNowException extends RuntimeException{
    public StartNowException(){
        super("시작일이 현재보다 빠릅니다");
    }
}
