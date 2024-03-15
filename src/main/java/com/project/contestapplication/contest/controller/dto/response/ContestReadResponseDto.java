package com.project.contestapplication.contest.controller.dto.response;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContestReadResponseDto {
    long id;
    String title;
    String content;
    long startAt;
    long endAt;
    ContestStatus contestStatus;
    User writer;

    public static ContestReadResponseDto from(Contest contest){
        return new ContestReadResponseDto(
                contest.getId(),
                contest.getTitle(),
                contest.getContent(),
                contest.getStartAt(),
                contest.getEndAt(),
                contest.getContestStatus(),
                contest.getWriter());
    }
}
