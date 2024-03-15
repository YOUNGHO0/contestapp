package com.project.contestapplication.contest.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RequiredArgsConstructor
@Component
public class ContestUpdateValidatorImpl implements ContestUpdateValidator{
    private final Clock clock;

    private void validate(Contest contest) {

        if(noTitle(contest))
            throw new NoTitleException();
        if(isPkZero(contest))
            throw new NoPkException();
        if(noContent(contest))
            throw new NoContentException();
        if(isStartAtBeforeNow(contest,clock))
            throw new StartNowException();
        if(isEndAtBeforeStartAt(contest))
            throw new StartEndException();

    }

    public void validate(Contest requestContest , Contest originalContest){

        validate(requestContest);

        if(isNotSameUser(requestContest, originalContest))
            throw new NoSameUserException();

        if(isContestStatusDifferent(requestContest,originalContest))
             throw new DifferentContestStatusException();

        if(isContestStatusNotPending(originalContest)) {
            if (isStartAtChanged(requestContest, originalContest))
                throw new UnchangeableException();
            if (isEndAtChanged(requestContest, originalContest))
                throw new UnchangeableException();
        }
    }
    private boolean isContestStatusNotPending(Contest contest){
        if(contest.getContestStatus()==ContestStatus.PENDING) return false;
        return true;
    }
    private boolean isEndAtChanged(Contest requestContest, Contest originalContest){
        if(requestContest.getEndAt() == originalContest.getEndAt()) return false;
        return true;
    }
    private boolean isStartAtChanged(Contest requestContest, Contest originalContest){
        if(requestContest.getStartAt() == originalContest.getStartAt()) return false;
        return true;
    }
    private boolean isContestStatusDifferent(Contest requestContest, Contest originalContest){
        if(requestContest.getContestStatus()== originalContest.getContestStatus()) return false;
        return true;
    }



    private boolean noContent(Contest contest){
        if(contest.getContent().length() ==0) return true;
        return false;
    }

    private boolean isPkAlreadyExist(Contest contest){
        if(contest.getId()!=0) return true;
        return false;
    }
    private boolean isStartAtBeforeNow(Contest contest, Clock clock){
        long startAt = contest.getStartAt();
//        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        long now = LocalDateTime.now(clock).toEpochSecond(ZoneOffset.UTC);
        if(startAt < now) return true;

        return false;
    }

    private boolean isEndAtBeforeStartAt(Contest contest){
        if(contest.getEndAt() < contest.getStartAt()) return true;
        return false;
    }

    private boolean noTitle(Contest contest){
        if(contest.getTitle().length()==0) return true;
        return false;
    }

    private boolean isPkZero(Contest contest){
        if(contest.getId()==0) return true;
        return false;
    }

    private boolean isNotSameUser(Contest requestContest, Contest originalContest){
        if(requestContest.getWriter().equals(originalContest.getWriter())) return false;
        return true;
    }


}
