package com.project.contestapplication.userplay.service;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;
import com.project.contestapplication.userplay.exception.MultipleUserPlayException;
import com.project.contestapplication.userplay.repository.UserPlayRepository;
import com.project.contestapplication.userplay.service.validator.UserPlayCreateValidator;
import com.project.contestapplication.userplay.service.validator.UserPlayDeleteValidator;
import com.project.contestapplication.userplay.service.validator.UserPlayUpdateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPlayManagerImpl implements UserPlayManager {
    private final UserPlayRepository userPlayRepository;
    private final UserPlayCreateValidator userPlayCreateValidator;
    private final UserPlayUpdateValidator userPlayUpdateValidator;
    private final UserPlayDeleteValidator userPlayDeleteValidator;
    @Override
    public void createUserPlayOn(UserPlay userPlay) {
        checkUserAlreadyCreate(userPlayRepository,userPlay);
        userPlayCreateValidator.validate(userPlay);
        userPlayRepository.save(userPlay);
    }

    @Override
    public UserPlay readUserPlay(long userPlayId) {

        Optional<UserPlay> optionalUserPlay = userPlayRepository.findById(userPlayId);
        if(optionalUserPlay.isEmpty()) throw new RuntimeException("작성 연주가 없습니다");
        return optionalUserPlay.get();


    }

    @Override
    public UserPlay updateUserPlay(UserPlay userPlay) {
        Optional<UserPlay> optionalUserPlay = userPlayRepository.findByWriter(userPlay.getWriter());
        if(optionalUserPlay.isEmpty()) throw new RuntimeException("작성한 글이 없습니다");
        UserPlay originalUserPlay = optionalUserPlay.get();
        userPlayUpdateValidator.validate(userPlay,originalUserPlay);
        userPlayRepository.save(userPlay);
        return userPlay;
    }

    @Override
    public void deleteUserPlay(long userPlayId, User writer) {
        Optional<UserPlay> optionalUserPlay = userPlayRepository.findById(userPlayId);
        if(optionalUserPlay.isEmpty()) throw new RuntimeException();
        userPlayDeleteValidator.validate(optionalUserPlay.get(),writer);
        userPlayRepository.delete(optionalUserPlay.get());

    }

    private void checkUserAlreadyCreate(UserPlayRepository userPlayRepository, UserPlay userPlay){
        if(userPlayRepository.findByWriter(userPlay.getWriter()).isPresent())
            throw new MultipleUserPlayException();

    }
}
