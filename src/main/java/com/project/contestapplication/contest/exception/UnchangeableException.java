package com.project.contestapplication.contest.exception;

public class UnchangeableException extends RuntimeException{
    public UnchangeableException(){
        super("현재는 변경할 수 없습니다.");
    }
}
