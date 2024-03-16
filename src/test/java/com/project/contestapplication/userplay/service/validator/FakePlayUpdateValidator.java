package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.userplay.domain.UserPlay;

public class FakePlayUpdateValidator implements UserPlayUpdateValidator{
    public int callCount = 0;
    @Override
    public void validate(UserPlay requestUserPlay, UserPlay originalUserPlay) {
        callCount++;
    }
}
