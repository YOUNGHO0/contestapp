package com.project.contestapplication.contest.exception.handler;

import com.project.contestapplication.contest.exception.DifferentContestStatusException;
import com.project.contestapplication.contest.exception.IdException;
import com.project.contestapplication.contest.exception.NoContestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ContestExceptionHandler {

    @ExceptionHandler(DifferentContestStatusException.class)
    public ResponseEntity differentContest(){
        return ResponseEntity.badRequest().body("비정상적인 접근입니다");
    }
    @ExceptionHandler(IdException.class)
    public ResponseEntity idException(){
        return ResponseEntity.badRequest().body("비정상적인 접근입니다");
    }
    @ExceptionHandler(NoContestException.class)
    public ResponseEntity noContest(){
        return ResponseEntity.badRequest().body("존재하지 않는 대회 게시글입니다");
    }



}
