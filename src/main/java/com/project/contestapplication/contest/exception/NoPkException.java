package com.project.contestapplication.contest.exception;

public class NoPkException extends RuntimeException{
    public NoPkException(){
        super("게시글 id가 없습니다");
    }
}
