package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.userplay.domain.UserPlay;

public interface UserPlayUpdateValidator {
    void validate(UserPlay requestUserPlay,UserPlay originalUserPlay);
}
