package com.project.contestapplication.contest.controller.dto.request;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContestCreateDto {
    String title;
    String content;
    long startAt;
    long endAt;

    public static Contest toContest(ContestCreateDto contestCreateDto,User writer){

        return new Contest(
                0L,
                contestCreateDto.title,
                contestCreateDto.content,
                contestCreateDto.startAt,
                contestCreateDto.endAt,
                writer,
                ContestStatus.PENDING
        );

    }
}

