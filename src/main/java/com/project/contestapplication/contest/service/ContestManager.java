package com.project.contestapplication.contest.service;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.user.domain.User;

public interface ContestManager {

    boolean createContest(Contest contest);
    boolean updateContest(Contest contest);
    Contest readContest(Long contestNumber);
    boolean deleteContest(Long contestNumber, User user);

}
