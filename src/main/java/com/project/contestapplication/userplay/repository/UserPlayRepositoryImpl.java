package com.project.contestapplication.userplay.repository;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.user.infrastructre.UserEntity;
import com.project.contestapplication.userplay.domain.UserPlay;
import com.project.contestapplication.userplay.infrastructure.UserPlayEntity;
import com.project.contestapplication.userplay.infrastructure.UserPlayJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@RequiredArgsConstructor
@Repository
public class UserPlayRepositoryImpl implements UserPlayRepository{
    private final UserPlayJpaRepository userPlayJpaRepository;
    @Override
    public UserPlay save(UserPlay userPlay) {
        UserPlayEntity save = userPlayJpaRepository.save(UserPlayEntity.from(userPlay));
        return UserPlayEntity.toUserPlay(save);
    }

    @Override
    public void delete(UserPlay userPlay) {

        userPlayJpaRepository.delete(UserPlayEntity.from(userPlay));
    }

    @Override
    public Optional<UserPlay> findById(long userPlayId) {

        Optional<UserPlayEntity> optionalUserPlayEntity = userPlayJpaRepository.findById(userPlayId);
        if(optionalUserPlayEntity.isEmpty()) return Optional.ofNullable(null);

        return Optional.of(
                UserPlayEntity.toUserPlay(optionalUserPlayEntity.get())
        );
    }

    @Override
    public Optional<UserPlay> findByWriter(User writer) {
        Optional<UserPlayEntity> optionalUserPlayEntity = userPlayJpaRepository
                .findByWriter(UserEntity.from(writer));

        if(optionalUserPlayEntity.isEmpty()) return Optional.ofNullable(null);

        return Optional.of(
                UserPlayEntity.toUserPlay(optionalUserPlayEntity.get())
        );

    }

}
