package com.project.contestapplication.user.repository;

import com.project.contestapplication.user.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);
    boolean findByNickname(String nickName);
    boolean findById(Long id);
    Optional<User> findByUserId(String userId);
}
