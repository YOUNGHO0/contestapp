package com.project.contestapplication.userplay.exception;

public class NotDeletableException extends RuntimeException {

    public NotDeletableException(){
        super("삭제가 불가능합니다");
    }
}
