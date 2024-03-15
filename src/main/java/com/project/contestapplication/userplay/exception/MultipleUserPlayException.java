package com.project.contestapplication.userplay.exception;

public class MultipleUserPlayException extends RuntimeException{
    public MultipleUserPlayException(){
        super("한 개의 연주만 올릴 수 있습니다");
    }
}
