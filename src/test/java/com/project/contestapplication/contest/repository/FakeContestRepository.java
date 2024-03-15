package com.project.contestapplication.contest.repository;

import com.project.contestapplication.contest.domain.Contest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeContestRepository implements ContestRepository{

    public List<Contest> list = new ArrayList<>();
    long id =0;
    @Override
    public boolean save(Contest contest) {
        long contestId;
        if(contest.getId()==0)contestId = ++id;
        else contestId = contest.getId();

        Contest saveContest = new Contest(
                contestId,
                contest.getTitle(),
                contest.getContent(),
                contest.getStartAt(),
                contest.getEndAt(),
                contest.getWriter(),
                contest.getContestStatus()
                );
        list.add(saveContest);
        return true;
    }

    @Override
    public boolean delete(Contest contest) {
        list.remove(contest);
        return true;
    }

    @Override
    public Optional<Contest> findById(long id) {
        return list.stream().filter(contest -> contest.getId() == id).findFirst();
    }


}
