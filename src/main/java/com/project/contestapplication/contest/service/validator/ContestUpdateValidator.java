package com.project.contestapplication.contest.service.validator;


import com.project.contestapplication.contest.domain.Contest;

public interface ContestUpdateValidator {

    void validate(Contest requestContest , Contest originalContest);
}
