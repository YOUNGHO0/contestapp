package com.project.contestapplication.userplay.service;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;

public interface UserPlayManager {

    void createUserPlayOn(UserPlay userPlay);
    UserPlay readUserPlay(long userPlayId);
    UserPlay updateUserPlay(UserPlay userPlay);
    void deleteUserPlay(long userPlayId, User writer);
}
