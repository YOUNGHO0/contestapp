package com.project.contestapplication.contest.service;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.exception.NoTitleException;
import com.project.contestapplication.contest.exception.UnchangeableException;
import com.project.contestapplication.contest.repository.ContestRepository;
import com.project.contestapplication.contest.repository.FakeContestRepository;
import com.project.contestapplication.contest.service.validator.*;
import com.project.contestapplication.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;

class ContestManagerImplTest {

    private Clock getFixedClock(LocalDateTime localDateTime){
        LocalDateTime dateTime = localDateTime;
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        return Clock.fixed(instant, seoulZoneId);
    }
    @Test
    public void updateValidator가_올바르게_작동하는지_검사한다(){
        //given
        ContestRepository contestRepository = new FakeContestRepository();
        Clock fixedClock = getFixedClock(LocalDateTime.of(2024,3,2,0,0));
        ContestCreateValidator contestCreateValidator = new ContestCreateValidatorImpl(fixedClock);
        ContestUpdateValidator contestUpdateValidator = new ContestUpdateValidatorImpl(fixedClock);
        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.PROCEEDING)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .contestStatus(ContestStatus.PROCEEDING)
                .build();

        contestRepository.save(originalContest);
        ContestManager contestManager = new ContestManagerImpl(
                contestRepository,
                contestCreateValidator,
                contestUpdateValidator,
                contestDeleteValidator
        );

        //when then
        Assertions.assertThatThrownBy(()->contestManager.updateContest(requestContest)).isInstanceOf(UnchangeableException.class);

    }

    @Test
    public void createValidator가_올바르게_작동하는지_검사한다(){
        //given
        ContestRepository contestRepository = new FakeContestRepository();
        Clock fixedClock = getFixedClock(LocalDateTime.of(2024,3,2,0,0));
        ContestCreateValidator contestCreateValidator = new ContestCreateValidatorImpl(fixedClock);
        ContestUpdateValidator contestUpdateValidator = new ContestUpdateValidatorImpl(fixedClock);
        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest requestContest = Contest.builder()
                .id(0)
                .title("")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.PENDING)
                .build();

        ContestManager contestManager = new ContestManagerImpl(
                contestRepository,
                contestCreateValidator,
                contestUpdateValidator,
                contestDeleteValidator
        );

        //when then
        Assertions.assertThatThrownBy(()->contestManager.createContest(requestContest)).isInstanceOf(NoTitleException.class);
    }

    @Test
    public void deleteValidator가_올바르게_작동하는지_검사한다(){

        //given
        ContestRepository contestRepository = new FakeContestRepository();
        Clock fixedClock = getFixedClock(LocalDateTime.of(2024,3,2,0,0));
        ContestCreateValidator contestCreateValidator = new ContestCreateValidatorImpl(fixedClock);
        ContestUpdateValidator contestUpdateValidator = new ContestUpdateValidatorImpl(fixedClock);
        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.PROCEEDING)
                .build();
        contestRepository.save(originalContest);
        ContestManager contestManager = new ContestManagerImpl(
                contestRepository,
                contestCreateValidator,
                contestUpdateValidator,
                contestDeleteValidator
        );

        //when then
        Assertions.assertThatThrownBy(()->contestManager.deleteContest(1L,user))
                .isInstanceOf(UnchangeableException.class);

    }

    @Test
    public void 정상적인_요청에_대해서_create되는지_확인한다(){

        ContestRepository contestRepository = new FakeContestRepository();
        Clock fixedClock = getFixedClock(LocalDateTime.of(2024,3,2,0,0));
        ContestCreateValidator contestCreateValidator = new ContestCreateValidatorImpl(fixedClock);
        ContestUpdateValidator contestUpdateValidator = new ContestUpdateValidatorImpl(fixedClock);
        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest requestContest = Contest.builder()
                .id(0)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.PENDING)
                .build();

        Contest savedContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.PENDING)
                .build();

        ContestManager contestManager = new ContestManagerImpl(
                contestRepository,
                contestCreateValidator,
                contestUpdateValidator,
                contestDeleteValidator
        );

        //when
        contestManager.createContest(requestContest);
        // then
        Assertions.assertThat(contestRepository.findById(1)).isPresent();
        Assertions.assertThat(contestRepository.findById(1).get()).isEqualTo(savedContest);

    }


    @Test
    public void 정상적인_요청에_대해서_read되는지_확인한다(){

        ContestRepository contestRepository = new FakeContestRepository();
        Clock fixedClock = getFixedClock(LocalDateTime.of(2024,3,2,0,0));
        ContestCreateValidator contestCreateValidator = new ContestCreateValidatorImpl(fixedClock);
        ContestUpdateValidator contestUpdateValidator = new ContestUpdateValidatorImpl(fixedClock);
        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User user = new User(
                1,
                "test",
                "hello",
                "hello",
                10,
                20);

        Contest requestContest = new Contest(
                0,
                "테스트",
                "수정 테스트",
                LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC),
                LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC),
                user,
                ContestStatus.PENDING);

        Contest savedContest = new Contest(
                1,
                "테스트",
                "수정 테스트",
                LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC),
                LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC),
                user,
                ContestStatus.PENDING);


        ContestManager contestManager = new ContestManagerImpl(
                contestRepository,
                contestCreateValidator,
                contestUpdateValidator,
                contestDeleteValidator
        );

        //when
        contestManager.createContest(requestContest);
        Contest getContest = contestManager.readContest(1L);
        // then
        Assertions.assertThat(savedContest).isEqualTo(getContest);

    }



    @Test
    public void 정상적인_요청에_대해서_update되는지_확인한다(){
        // 대회 시작전 대회 시작후
        ContestRepository contestRepository = new FakeContestRepository();
        Clock fixedClock = getFixedClock(LocalDateTime.of(2024,3,2,0,0));
        ContestCreateValidator contestCreateValidator = new ContestCreateValidatorImpl(fixedClock);
        ContestUpdateValidator contestUpdateValidator = new ContestUpdateValidatorImpl(fixedClock);
        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User user = new User(
                1,
                "test",
                "hello",
                "hello",
                10,
                20);

        Contest requestContest = new Contest(
                3,
                "테스트업데이트 요청",
                "수정 테스트요청",
                LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC),
                LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC),
                user,
                ContestStatus.PENDING);

        Contest savedContest = new Contest(
                3,
                "테스트",
                "수정 테스트",
                LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC),
                LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC),
                user,
                ContestStatus.PENDING);


        ContestManager contestManager = new ContestManagerImpl(
                contestRepository,
                contestCreateValidator,
                contestUpdateValidator,
                contestDeleteValidator
        );
        contestRepository.save(savedContest);
        //when
        contestManager.updateContest(requestContest);
        Contest getContest = contestManager.readContest(3L);
        // then
        Assertions.assertThat(requestContest).isEqualTo(getContest);

    }

    @Test
    public void 정상적인_요청에_대해_올바르게_삭제되는지_확인한다(){

        ContestRepository contestRepository = new FakeContestRepository();
        Clock fixedClock = getFixedClock(LocalDateTime.of(2024,3,2,0,0));
        ContestCreateValidator contestCreateValidator = new ContestCreateValidatorImpl(fixedClock);
        ContestUpdateValidator contestUpdateValidator = new ContestUpdateValidatorImpl(fixedClock);
        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User user = new User(
                1,
                "test",
                "hello",
                "hello",
                10,
                20);


        Contest savedContest = new Contest(
                1,
                "테스트",
                "수정 테스트",
                LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC),
                LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC),
                user,
                ContestStatus.PENDING);


        ContestManager contestManager = new ContestManagerImpl(
                contestRepository,
                contestCreateValidator,
                contestUpdateValidator,
                contestDeleteValidator
        );
        //when
        contestRepository.save(savedContest);

        // then
        Assertions.assertThatNoException().isThrownBy(() -> contestManager.deleteContest(1L,user));
    }
}