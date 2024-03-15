package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.exception.DifferentContestStatusException;
import com.project.contestapplication.contest.exception.NoSameUserException;
import com.project.contestapplication.contest.exception.NoTitleException;
import com.project.contestapplication.contest.exception.UnchangeableException;
import com.project.contestapplication.userplay.domain.UserPlay;
import org.springframework.stereotype.Component;

@Component
public class UserPlayUpdateValidatorImpl implements UserPlayUpdateValidator{


    @Override
    public void validate(UserPlay requestUserPlay, UserPlay originalUserPlay) {

        if(originalUserPlay ==null){
            throw new RuntimeException("유효하지 않은 요청입니다");
        }
        if(isTitleEmpty(requestUserPlay)){
             throw new NoTitleException();
         }
         if(isWriterDifferent(requestUserPlay,originalUserPlay)){
             throw new NoSameUserException();
        }
        if(isContestStatusNotProceeding(originalUserPlay)){
            throw new UnchangeableException();
        }



    }

    private boolean isContestStatusDifferent(UserPlay requestUserPlay, UserPlay originalUserPlay){
        if(originalUserPlay.getContest().getContestStatus() != requestUserPlay.getContest().getContestStatus())
        return true;

        else return false;
    }

    private boolean isTitleEmpty(UserPlay requestUserPlay){
        if(requestUserPlay.getPlayTitle().length()==0) return true;
        return false;
    }
    private boolean isWriterDifferent(UserPlay requestPlay, UserPlay originalPlay){
        if(!requestPlay.getWriter().equals(originalPlay.getWriter())) return true;
        return false;
    }

    private boolean isContestStatusNotProceeding(UserPlay userPlay){
        if(userPlay.getContest().getContestStatus() == ContestStatus.PROCEEDING) return false;
        return true;

    }
}
