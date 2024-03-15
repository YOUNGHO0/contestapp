package com.project.contestapplication.contest.exception;

public class NoSameUserException extends RuntimeException{
    public NoSameUserException(){
        super("게시글을 수정할 수 있는 권한이 없습니다");
    }
}
