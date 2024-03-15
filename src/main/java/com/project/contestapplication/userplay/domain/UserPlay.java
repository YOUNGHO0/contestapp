package com.project.contestapplication.userplay.domain;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.storage.domain.File;
import com.project.contestapplication.user.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class UserPlay {

    long id;
    Contest contest;
    String playTitle;
    File audioFile;
    User writer;

    public UserPlay(long id, Contest contest, String playTitle, File audioFile, User writer){
        this.id = id;
        this.contest = contest;
        this.playTitle = playTitle;
        this.audioFile = audioFile;
        this.writer = writer;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UserPlay)) return false;
        UserPlay userPlay = (UserPlay) obj;
        return userPlay.id==this.id && userPlay.getWriter().equals(this.getWriter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id,this.getWriter());
    }
}
