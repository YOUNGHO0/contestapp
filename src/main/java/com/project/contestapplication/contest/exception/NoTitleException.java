package com.project.contestapplication.contest.exception;

public class NoTitleException extends RuntimeException{
    public NoTitleException(){
        super("제목이 없습니다");
    }
}
