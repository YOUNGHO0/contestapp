package com.project.contestapplication.contest.service;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.exception.NoContestException;
import com.project.contestapplication.contest.repository.ContestRepository;
import com.project.contestapplication.contest.service.validator.ContestCreateValidator;
import com.project.contestapplication.contest.service.validator.ContestDeleteValidator;
import com.project.contestapplication.contest.service.validator.ContestUpdateValidator;
import com.project.contestapplication.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class ContestManagerImpl implements ContestManager{
    private final ContestRepository contestRepository;
    private final ContestCreateValidator contestCreateValidator;
    private final ContestUpdateValidator contestUpdateValidator;
    private final ContestDeleteValidator contestDeleteValidator;
    @Override
    public boolean createContest(Contest contest) {
        contestCreateValidator.validate(contest);
        contestRepository.save(contest);
        return true;
    }

    @Override
    public boolean updateContest(Contest updateContest) {
        Optional<Contest> optionalOriginalContest = getOriginalContestBy(updateContest.getId());
        Contest originalContest = optionalOriginalContest.get();
        contestUpdateValidator.validate(updateContest,originalContest);
        contestRepository.save(updateContest);
        return true;
    }

    private Optional<Contest> getOriginalContestBy(long id) {
        Optional<Contest> optionalOriginalContest = contestRepository.findById(id);
        if(optionalOriginalContest.isEmpty()) throw new NoContestException();
        return optionalOriginalContest;
    }

    @Override
    public Contest readContest(Long contestNumber) {
        Optional<Contest> optionalContest = contestRepository.findById(contestNumber);
        if(optionalContest.isEmpty()) throw new NoContestException();
        return optionalContest.get();
    }

    @Override
    public boolean deleteContest(Long contestId, User user) {
        Optional<Contest> optionalContest = contestRepository.findById(contestId);
        if(optionalContest.isEmpty()) throw new NoContestException();
        Contest contest = optionalContest.get();
        contestDeleteValidator.validate(contestId,user,contest);
        contestRepository.delete(contest);
        return true;
    }
}
