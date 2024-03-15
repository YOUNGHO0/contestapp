package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.userplay.domain.UserPlay;
import com.project.contestapplication.userplay.exception.InvalidContestException;
import com.project.contestapplication.userplay.exception.MultipleUserPlayException;
import com.project.contestapplication.userplay.repository.UserPlayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPlayCreateValidatorImpl implements UserPlayCreateValidator{

    @Override
    public void validate(UserPlay userPlay) {
        if(userPlay.getContest().getContestStatus() != ContestStatus.PROCEEDING) throw new InvalidContestException();

    }
}
