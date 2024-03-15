package com.project.contestapplication.userplay.service;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;

public interface UserPlayService {

    void create(UserPlay userPlay);
    UserPlay read(long userPlayId);
    UserPlay update(UserPlay userPlay);
    void delete(long userPlayId, User writer);
}
