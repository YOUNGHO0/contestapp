package com.project.contestapplication.userplay.repository;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;

import java.util.Optional;

public interface UserPlayRepository {

    UserPlay save(UserPlay userPlay);
    void delete(UserPlay userPlay);
    Optional<UserPlay> findById(long userPlayId);
    Optional<UserPlay> findByWriter(User writer);


}
