package com.project.contestapplication.contest.repository;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class FakeContestRepositoryTest {
    @Test
    public void save_테스트(){
        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest contest = new Contest(
                1,
                " 테스트 타이틀",
                "테스트 내용",
                10,
                20,
                user,
                ContestStatus.PENDING);

        Contest savedContest = new Contest(
                1,
                " 테스트 타이틀",
                "테스트 내용",
                10,
                20,
                user,
                ContestStatus.PENDING);

        FakeContestRepository fakeContestRepository = new FakeContestRepository();
        List<Contest> list= fakeContestRepository.list;
        //when
        fakeContestRepository.save(contest);

        Assertions.assertThat(list.size()).isEqualTo(1);
        Assertions.assertThat(list.get(0)).isEqualTo(contest);

    }
    @Test
    public void PK_0일때_id_값을_증가시키며_저장한다(){
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest contest = new Contest(
                0,
                " 테스트 타이틀",
                "테스트 내용",
                10,
                20,
                user,
                ContestStatus.PENDING);

        Contest savedContest = new Contest(
                1,
                " 테스트 타이틀",
                "테스트 내용",
                10,
                20,
                user,
                ContestStatus.PENDING);

        FakeContestRepository fakeContestRepository = new FakeContestRepository();
        List<Contest> list= fakeContestRepository.list;
        //when
        fakeContestRepository.save(contest);

        Assertions.assertThat(list.size()).isEqualTo(1);
        System.out.println(list.get(0).getId());
        Assertions.assertThat(list.get(0)).isEqualTo(savedContest);
    }
    @Test
    public void Delete_테스트(){

        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest contest = new Contest(
                1,
                " 테스트 타이틀",
                "테스트 내용",
                10,
                20,
                user,
                ContestStatus.PENDING);

        FakeContestRepository fakeContestRepository = new FakeContestRepository();
        List<Contest> list= fakeContestRepository.list;
        fakeContestRepository.save(contest);

        //when
        fakeContestRepository.delete(contest);

        //then
        Assertions.assertThat(list.size()).isEqualTo(0);

    }

    @Test
    public void findById_테스트(){
        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest contest = new Contest(
                1,
                " 테스트 타이틀",
                "테스트 내용",
                10,
                20,
                user,
                ContestStatus.PENDING);

        FakeContestRepository fakeContestRepository = new FakeContestRepository();
        List<Contest> list= fakeContestRepository.list;
        fakeContestRepository.save(contest);

        //when
        Optional<Contest> optionalContest = fakeContestRepository.findById(1);

        //then
        Assertions.assertThat(optionalContest).isPresent();
        Contest foundContest = optionalContest.get();
        Assertions.assertThat(foundContest.getId()).isEqualTo(1);


    }



}