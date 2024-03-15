package com.project.contestapplication.contest.infrastructure;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.user.infrastructre.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ContestEntity {

    public static ContestEntity from(Contest contest){
        return new ContestEntity(
                contest.getId(),
                contest.getTitle(),
                contest.getContent(),
                contest.getStartAt(),
                contest.getEndAt(),
                UserEntity.from(contest.getWriter()),
                contest.getContestStatus()
                );
    }
    public static Contest toContest(ContestEntity contestEntity){
        return new Contest(
                contestEntity.id,
                contestEntity.title,
                contestEntity.content,
                contestEntity.startAt,
                contestEntity.endAt,
                UserEntity.toUser(contestEntity.userEntity),
                contestEntity.contestStatus);


    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private long startAt;
    private long endAt;
    private ContestStatus contestStatus;
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    @Builder
    public ContestEntity(long id ,String title, String content, long startAt, long endAt,UserEntity userEntity,ContestStatus contestStatus){
        this.id =id;
        this.title = title;
        this.content = content;
        this.startAt = startAt;
        this.endAt = endAt;
        this.userEntity =userEntity;
        this.contestStatus = contestStatus;
    }

}
