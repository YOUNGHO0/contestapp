package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.exception.NoSameUserException;
import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;
import com.project.contestapplication.userplay.exception.NotDeletableException;
import org.springframework.stereotype.Component;

@Component
public class UserPlayDeleteValidatorImpl implements UserPlayDeleteValidator {
    @Override
    public void validate(UserPlay originalUserPlay,User user) {
        if(isUserNotSame(originalUserPlay,user)){
            throw new NoSameUserException();
        }
        if(isContestNotProceeding(originalUserPlay)){
            throw new NotDeletableException();
        }
    }

    private boolean isContestNotProceeding(UserPlay originalUserPlay){
        if(originalUserPlay.getContest().getContestStatus()== ContestStatus.PROCEEDING) return false;
        return true;
    }
    private boolean isUserNotSame(UserPlay userPlay, User user ){
        if(userPlay.getWriter().equals(user)) return false;
        return true;
    }
}
