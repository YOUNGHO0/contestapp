package com.project.contestapplication.contest.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.exception.*;
import com.project.contestapplication.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;

class ContestUpdateValidatorImplTest {

    @Test
    public void 제목이_없다면_예외를_던진다() {

        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(Clock.systemDefaultZone());

        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest requestContest = Contest.builder()
                .id(0)
                .title("")
                .startAt(19)
                .endAt(10)
                .content("테스트")
                .writer(user)
                .build();


        Contest originalContest = Contest.builder()
                .id(0)
                .title("")
                .startAt(19)
                .endAt(10)
                .content("테스트")
                .writer(user)
                .build();

        //given

        //when then
        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(NoTitleException.class);

    }

    @Test
    public void PK가_0이면_예외를_던진다() {
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(Clock.systemDefaultZone());
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest requestContest = Contest.builder()
                .id(0)
                .title("테스트")
                .startAt(19)
                .endAt(10)
                .content("테스트")
                .writer(user)
                .build();

        Contest originalContest = Contest.builder()
                .id(0)
                .title("테스트")
                .startAt(19)
                .endAt(10)
                .content("테스트")
                .writer(user)
                .build();


        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(NoPkException.class);

    }


    @Test
    public void 내용이_없다면_예외를_던진다() {
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(Clock.systemDefaultZone());
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest requestContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(19)
                .endAt(10)
                .content("")
                .writer(user)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(19)
                .endAt(10)
                .content("")
                .writer(user)
                .build();


        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(NoContentException.class);


    }


    @Test
    public void 시작_날짜가_현재보다_전이라면_예외를_던진다() {

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);

        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest requestContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 2, 4, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 5, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 2, 4, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 5, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .build();


        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(StartNowException.class);


    }

    @Test
    public void 시작_날짜보다_종료_날짜가_앞이라면_예외를_던진다() {


        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);

        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest requestContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 5, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 5, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .build();

        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(StartEndException.class);

    }


    @Test
    public void 진행중이_아닌_대회는_제약없이_수정이_가능하다() {

        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.PENDING)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .contestStatus(ContestStatus.PENDING)
                .build();

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatNoException().isThrownBy(() -> validator.validate(requestContest, originalContest));


    }

    @Test
    public void 진행_상태가_다르면_예외를_던진다() {

        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
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
                .contestStatus(ContestStatus.PENDING)
                .build();

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 4, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(DifferentContestStatusException.class);


    }


    @Test
    public void 대회진행상태가_진행중이면_시작일을_변경할_수_없다() {

        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 7, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
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

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(UnchangeableException.class);


    }


    @Test
    public void 대회진행상태가_대회제출마감이면_시작일을_변경할_수_없다() {

        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 7, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.REGISTER_END)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .contestStatus(ContestStatus.REGISTER_END)
                .build();

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(UnchangeableException.class);


    }

    @Test
    public void 대회진행상태가_최종마감이면_시작일을_변경할_수_없다() {

        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 7, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.FINAL_END)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .contestStatus(ContestStatus.FINAL_END)
                .build();

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(UnchangeableException.class);


    }

    ///

    @Test
    public void 대회진행상태가_진행중이면_종료일을_변경할_수_없다() {

        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

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

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(UnchangeableException.class);


    }


    @Test
    public void 대회진행상태가_대회제출마감이면_종료일을_변경할_수_없다() {

        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.REGISTER_END)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .contestStatus(ContestStatus.REGISTER_END)
                .build();

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(UnchangeableException.class);


    }

    @Test
    public void 대회진행상태가_최종마감이면_종료일을_변경할_수_없다() {

        //given
        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.FINAL_END)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .contestStatus(ContestStatus.FINAL_END)
                .build();

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatThrownBy(() -> validator.validate(requestContest, originalContest))
                .isInstanceOf(UnchangeableException.class);


    }

    @Test
    public void 글_작성자가_아니면_예외를_던진다(){

        User user = new User(
                1,
                "pow@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);

        User user2 = new User(
                3,
                "po123w@gmail.com",
                "안녕하세요",
                "패스워드",
                10,
                12);


        Contest requestContest = Contest.builder()
                .id(1)
                .title("수정 테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 12, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("수정 테스트")
                .writer(user)
                .contestStatus(ContestStatus.PENDING)
                .build();

        Contest originalContest = Contest.builder()
                .id(1)
                .title("테스트")
                .startAt(LocalDateTime.of(2024, 3, 6, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024, 3, 10, 0, 0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user2)
                .contestStatus(ContestStatus.PENDING)
                .build();

        LocalDateTime dateTime = LocalDateTime.of(2024, 3, 1, 0, 0);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant, seoulZoneId);
        ContestUpdateValidator validator = new ContestUpdateValidatorImpl(fixedClock);


        //when then
        Assertions.assertThatThrownBy(()-> validator.validate(requestContest,originalContest)).isInstanceOf(NoSameUserException.class);

    }




}