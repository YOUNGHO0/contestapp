package com.project.contestapplication.contest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContestStatus {
    PROCEEDING("진행 중"),
    PENDING("예정"),
    REGISTER_END("대회제출 마감"),
    FINAL_END("최종 마감");
    private final String name;
}
