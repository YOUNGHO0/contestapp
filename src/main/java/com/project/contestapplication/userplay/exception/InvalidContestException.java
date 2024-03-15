package com.project.contestapplication.userplay.exception;

public class InvalidContestException extends RuntimeException{

    public InvalidContestException(){
        super("유효하지 않은 대회입니다");
    }
}
