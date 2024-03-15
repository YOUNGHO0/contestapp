package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;

public interface UserPlayDeleteValidator {
    void validate(UserPlay userPlay, User user);
}
