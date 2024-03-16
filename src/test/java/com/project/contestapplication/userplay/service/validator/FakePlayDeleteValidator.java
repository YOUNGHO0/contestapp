package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;

public class FakePlayDeleteValidator implements UserPlayDeleteValidator{
    public int callCount = 0;

    @Override
    public void validate(UserPlay userPlay, User user) {
        callCount++;
    }
}
