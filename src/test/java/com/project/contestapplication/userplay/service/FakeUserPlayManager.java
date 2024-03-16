package com.project.contestapplication.userplay.service;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;

public class FakeUserPlayManager implements UserPlayManager{
    int callCount =0;
    @Override
    public void createUserPlayOn(UserPlay userPlay) {
        callCount++;
    }

    @Override
    public UserPlay readUserPlay(long userPlayId) {
        callCount++;
        return new UserPlay(0,null,null,null,null);
    }

    @Override
    public UserPlay updateUserPlay(UserPlay userPlay) {
        callCount++;
        return new UserPlay(0,null,null,null,null);
    }

    @Override
    public void deleteUserPlay(long userPlayId, User writer) {
        callCount++;
    }
}
