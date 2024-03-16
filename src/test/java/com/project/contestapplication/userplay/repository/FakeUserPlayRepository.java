package com.project.contestapplication.userplay.repository;



import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeUserPlayRepository implements UserPlayRepository{

    public List<UserPlay> list = new ArrayList<>();
    public long id =0;
    long callCount =0;
    @Override
    public UserPlay save(UserPlay userPlay) {
        callCount++;
        long userPlayId;
        if(userPlay.getId()==0)userPlayId = ++id;
        else userPlayId = userPlay.getId();

        UserPlay saveUserPlay = new UserPlay(
                userPlayId,
                userPlay.getContest(),
                userPlay.getPlayTitle(),
                userPlay.getAudioFile(),
                userPlay.getWriter()
        );
        if(list.contains(saveUserPlay))
            list.remove(saveUserPlay);
        list.add(saveUserPlay);
        return saveUserPlay;
    }

    @Override
    public void delete(UserPlay userPlay) {
        callCount++;
        list.remove(userPlay);
    }

    @Override
    public Optional<UserPlay> findById(long userPlayId) {
        callCount++;
        return list.stream().filter(userPlay -> userPlay.getId() == userPlayId).findFirst();
    }

    @Override
    public Optional<UserPlay> findByWriter(User writer) {
        callCount++;
       return list.stream().filter(userPlay -> userPlay.getWriter().equals(writer)).findFirst();
    }

}
