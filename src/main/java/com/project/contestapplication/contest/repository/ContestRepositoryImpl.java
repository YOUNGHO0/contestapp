package com.project.contestapplication.contest.repository;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.infrastructure.ContestEntity;
import com.project.contestapplication.contest.infrastructure.ContestJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class ContestRepositoryImpl implements ContestRepository{


    private final ContestJpaRepository contestJpaRepository;
    @Override
    public boolean save(Contest contest) {
        ContestEntity contestEntity = ContestEntity.from(contest);
        contestJpaRepository.save(contestEntity);
        return true;
    }

    @Override
    public boolean delete(Contest contest) {
        contestJpaRepository.delete(ContestEntity.from(contest));
        return true;
    }

    @Override
    public Optional<Contest> findById(long id) {
        Optional<ContestEntity> contestEntity = contestJpaRepository.findById(id);
        if(contestEntity.isPresent()){
            Contest contest = ContestEntity.toContest(contestEntity.get());
            Optional<Contest> optionalContest = Optional.of(contest);
            return optionalContest;
        }

        return Optional.ofNullable(null);
    }
}
