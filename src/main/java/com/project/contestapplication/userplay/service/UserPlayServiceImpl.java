package com.project.contestapplication.userplay.service;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPlayServiceImpl implements UserPlayService{

    private final UserPlayManager userPlayManager;
    @Override
    public void create(UserPlay userPlay) {
        userPlayManager.createUserPlayOn(userPlay);
    }

    @Override
    public UserPlay read(long userPlayId) {
        return userPlayManager.readUserPlay(userPlayId);
    }

    @Override
    public UserPlay update(UserPlay userPlay) {
        return userPlayManager.updateUserPlay(userPlay);
    }

    @Override
    public void delete(long userPlayId, User writer) {
        userPlayManager.deleteUserPlay(userPlayId, writer);
    }
}
