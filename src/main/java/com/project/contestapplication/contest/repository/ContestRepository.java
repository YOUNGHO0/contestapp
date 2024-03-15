package com.project.contestapplication.contest.repository;

import com.project.contestapplication.contest.domain.Contest;

import java.util.Optional;

public interface ContestRepository {

    boolean save(Contest contest);
    boolean delete(Contest contest);
    Optional<Contest> findById(long id);

}
