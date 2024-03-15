package com.project.contestapplication.contest.service;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService{


    private final ContestManager contestManager;

    @Override
    public boolean createContest(Contest contest) {
        contestManager.createContest(contest);
        return true;
    }

    @Override
    public boolean updateContest(Contest contest) {
        contestManager.updateContest(contest);
        return true;
    }

    @Override
    public Contest readContest(Long contestNumber) {
        Contest contest = contestManager.readContest(contestNumber);
        return contest;
    }

    @Override
    public boolean deleteContest(Long contestId, User user) {
        contestManager.deleteContest(contestId,user);
        return true;
    }
}
