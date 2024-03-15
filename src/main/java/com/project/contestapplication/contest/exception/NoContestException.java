package com.project.contestapplication.contest.exception;

public class NoContestException extends RuntimeException {
    public NoContestException(){
        super("존재하지 않는 게시글 입니다");
    }
}
