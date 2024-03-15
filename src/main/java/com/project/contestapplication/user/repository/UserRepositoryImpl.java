package com.project.contestapplication.user.repository;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.user.infrastructre.UserEntity;
import com.project.contestapplication.user.infrastructre.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepositoryImpl implements UserRepository{

    private final UserJpaRepository userJpaRepository;
    @Override
    public User save(User user) {
        UserEntity userEntity = UserEntity.from(user);
        userJpaRepository.save(userEntity);
        return UserEntity.toUser(userEntity);
    }

    @Override
    public boolean findByNickname(String nickName) {
        return false;
    }

    @Override
    public boolean findById(Long id) {
        return false;
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        Optional<UserEntity> userEntity = userJpaRepository.findByUserId(userId);
        if(userEntity.isPresent()) return Optional.of(UserEntity.toUser(userEntity.get()));
        else return Optional.ofNullable(null);

    }
}
