package com.project.contestapplication.contest.controller.dto.request;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.user.domain.User;

public class ContestUpdateDto {
    long id;
    String title;
    String content;
    long startAt;
    long endAt;
    ContestStatus contestStatus;
    User writer;

    public Contest toContest(User writer){
        return new Contest(id,this.title,this.content,this.startAt,this.endAt,writer,contestStatus);
    }
}
