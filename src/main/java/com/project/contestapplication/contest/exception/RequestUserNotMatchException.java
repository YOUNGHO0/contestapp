package com.project.contestapplication.contest.exception;

public class RequestUserNotMatchException extends RuntimeException{
    public RequestUserNotMatchException(){
        super(" 글 작성자가 아닙니다.");
    }
}
