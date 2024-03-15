package com.project.contestapplication.contest.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.exception.*;
import com.project.contestapplication.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;

class ContestCreateValidatorImplTest {

    @Test
    public void test(){
        LocalDateTime dateTime = LocalDateTime.of(2017, Month.JUNE, 15, 13, 39);
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime dateTime1 = ZonedDateTime.of(dateTime,seoulZoneId);
        Instant instant = dateTime1.toInstant();
        Clock fixedClock = Clock.fixed(instant,seoulZoneId);

        Assertions
                .assertThat(LocalDateTime.now(fixedClock).toEpochSecond(ZoneOffset.UTC))
                .isEqualTo( LocalDateTime.of(2017, Month.JUNE, 15, 13, 39).toEpochSecond(ZoneOffset.UTC));

        System.out.println(LocalDateTime.now(Clock.systemDefaultZone()));
    }

    @Test
    public void 제목이_없다면_예외를_던진다(){

        ContestCreateValidatorImpl validator  = new ContestCreateValidatorImpl(Clock.systemDefaultZone());
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest contest = Contest.builder()
                .id(0)
                .title("")
                .startAt(19)
                .endAt(10)
                .content("테스트")
                .writer(user)
                .build();

        //given

        //when then
        Assertions.assertThatThrownBy(()->validator.validate(contest))
                .isInstanceOf(NoTitleException.class);

    }


    @Test
    public void 내용이_없다면_예외를_던진다(){
        ContestCreateValidatorImpl validator  = new ContestCreateValidatorImpl(Clock.systemDefaultZone());
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest contest = Contest.builder()
                .id(0)
                .title("테스트")
                .startAt(19)
                .endAt(10)
                .content("")
                .writer(user)
                .build();


        Assertions.assertThatThrownBy(()->validator.validate(contest))
                .isInstanceOf(NoContentException.class);


    }
    @Test
    public void id가_0이_아니면_예외를_던진다(){

        ContestCreateValidatorImpl validator  = new ContestCreateValidatorImpl(Clock.systemDefaultZone());
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest contest = Contest.builder()
                .id(-1)
                .title("테스트")
                .startAt(19)
                .endAt(10)
                .content("테스트")
                .writer(user)
                .build();

        Assertions.assertThatThrownBy(()->validator.validate(contest))
                .isInstanceOf(IdException.class);

    }

    @Test
    public void 시작_날짜가_현재보다_전이라면_예외를_던진다(){

        LocalDateTime dateTime = LocalDateTime.of(2024,3,1,0,0 );
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant,seoulZoneId);

        ContestCreateValidatorImpl validator  = new ContestCreateValidatorImpl(fixedClock);
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest contest = Contest.builder()
                .id(0)
                .title("테스트")
                .startAt(LocalDateTime.of(2024,2,4,0,0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024,3,5,0,0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .build();


        Assertions.assertThatThrownBy(()->validator.validate(contest))
                .isInstanceOf(StartNowException.class);


    }

    @Test
    public void 시작_날짜보다_종료_날짜가_앞이라면_예외를_던진다(){


        LocalDateTime dateTime = LocalDateTime.of(2024,3,1,0,0 );
        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Clock fixedClock = Clock.fixed(instant,seoulZoneId);

        ContestCreateValidatorImpl validator  = new ContestCreateValidatorImpl(fixedClock);
        User user = User.builder()
                .email("test")
                .nickName("hello")
                .password("1234")
                .nickName("hello")
                .build();

        Contest contest = Contest.builder()
                .id(0)
                .title("테스트")
                .startAt(LocalDateTime.of(2024,3,6,0,0).toEpochSecond(ZoneOffset.UTC))
                .endAt(LocalDateTime.of(2024,3,5,0,0).toEpochSecond(ZoneOffset.UTC))
                .content("테스트")
                .writer(user)
                .build();

        Assertions.assertThatThrownBy(()->validator.validate(contest))
                .isInstanceOf(StartEndException.class);

    }









}