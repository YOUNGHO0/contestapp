package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.userplay.domain.UserPlay;

public class FakePlayCreateValidator implements UserPlayCreateValidator{
    public int callCount =0;
    @Override
    public void validate(UserPlay userPlay) {
        callCount++;
    }
}
