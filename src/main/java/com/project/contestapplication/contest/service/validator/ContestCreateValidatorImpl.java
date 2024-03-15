package com.project.contestapplication.contest.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
@RequiredArgsConstructor
public class ContestCreateValidatorImpl implements ContestCreateValidator {
    private final Clock clock;

    public void validate(Contest contest) {

        if(noTitle(contest))
            throw new NoTitleException();
        if(isPkAlreadyExist(contest))
            throw new IdException();
        if(noContent(contest))
            throw new NoContentException();
        if(isStartAtBeforeNow(contest,clock))
            throw new StartNowException();
        if(isEndAtBeforeStartAt(contest))
            throw new StartEndException();

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



}
