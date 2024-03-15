package com.project.contestapplication.contest.exception;

public class DifferentContestStatusException extends RuntimeException{

    public DifferentContestStatusException(){
        super("진행 상태가 서로 다릅니다.");
    }
}
