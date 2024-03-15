package com.project.contestapplication.contest.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.exception.RequestUserNotMatchException;
import com.project.contestapplication.contest.exception.UnchangeableException;
import com.project.contestapplication.user.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;

class ContestDeleteValidatorImplTest {

    @Test
    public void 글_작성자가_다르면_예외를_던진다(){
        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User originalUser = new User(
                1,
                "test",
                "hello",
                "hello",
                10,
                20);

        User differentUser = new User(
                2,
                "test2",
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
                originalUser,
                ContestStatus.PENDING);


        //when then
        Assertions.assertThatThrownBy(()->contestDeleteValidator.validate(1,differentUser,savedContest))
                .isInstanceOf(RequestUserNotMatchException.class);

    }

    @Test
    public void 대회_진행상태가_진행중이면_삭제할_수_없다(){

        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User originalUser = new User(
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
                originalUser,
                ContestStatus.PROCEEDING);


        //when then
        Assertions.assertThatThrownBy(()->contestDeleteValidator.validate(1,originalUser,savedContest))
                .isInstanceOf(UnchangeableException.class);


    }

    @Test
    public void 대회_진행상태가_대회_제출_마감이면_삭제할_수_없다(){

        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User originalUser = new User(
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
                originalUser,
                ContestStatus.REGISTER_END);


        //when then
        Assertions.assertThatThrownBy(()->contestDeleteValidator.validate(1,originalUser,savedContest))
                .isInstanceOf(UnchangeableException.class);


    }

    @Test
    public void 대회_진행상태가_최종_마감이면_삭제할_수_없다(){

        ContestDeleteValidator contestDeleteValidator = new ContestDeleteValidatorImpl();
        User originalUser = new User(
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
                originalUser,
                ContestStatus.FINAL_END);


        //when then
        Assertions.assertThatThrownBy(()->contestDeleteValidator.validate(1,originalUser,savedContest))
                .isInstanceOf(UnchangeableException.class);


    }

}