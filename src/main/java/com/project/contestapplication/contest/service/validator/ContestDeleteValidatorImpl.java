package com.project.contestapplication.contest.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.exception.RequestUserNotMatchException;
import com.project.contestapplication.contest.exception.UnchangeableException;
import com.project.contestapplication.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContestDeleteValidatorImpl implements  ContestDeleteValidator{




    @Override
    public void validate(long id, User requestUser, Contest contest) {

        if(isNotSameWriter(requestUser,contest)) throw new RequestUserNotMatchException();
        if(isContestStatusNotPending(contest)) throw new UnchangeableException();
    }

    private boolean isNotSameWriter(User requestUser, Contest contest){
        if(requestUser.equals(contest.getWriter())) return false;
        return true;
    }
    private boolean isContestStatusNotPending(Contest contest){
        if(contest.getContestStatus() != ContestStatus.PENDING) return true;
        return false;
    }
}
