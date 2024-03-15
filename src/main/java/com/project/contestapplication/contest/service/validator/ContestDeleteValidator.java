package com.project.contestapplication.contest.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.user.domain.User;

public interface ContestDeleteValidator {

    void validate(long id, User user, Contest contest);
}
