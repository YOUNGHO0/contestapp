package com.project.contestapplication.contest.domain;

import com.project.contestapplication.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public final class Contest {
    long id;
    String title;
    String content;
    long startAt;
    long endAt;
    ContestStatus contestStatus;
    User writer;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Contest)) return false;
        Contest contest = (Contest)obj;
        if(contest.id == this.id) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Builder
    public Contest(long id , String title, String content, long startAt, long endAt, User writer, ContestStatus contestStatus){
        this.id = id;
        this.title =title;
        this.content =content;
        this.startAt =startAt;
        this.endAt =endAt;
        this.writer =writer;
        this.contestStatus = contestStatus;
    }

}
