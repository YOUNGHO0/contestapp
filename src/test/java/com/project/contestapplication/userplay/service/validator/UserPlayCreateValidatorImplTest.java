package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.storage.domain.File;
import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;
import com.project.contestapplication.userplay.exception.InvalidContestException;
import com.project.contestapplication.userplay.exception.MultipleUserPlayException;
import com.project.contestapplication.userplay.repository.FakeUserPlayRepository;
import com.project.contestapplication.userplay.repository.UserPlayRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserPlayCreateValidatorImplTest {

    @Test
    public void 대회상태가_진행중이면_예외를_던지지_않는다(){
        //given
        User user = new User(
                1,
                "test@gmail.com",
                "테스트닉네임",
                "패스워드",
                10,
                20
        );
        Contest contest = new Contest(
                1,
                "대회",
                "대회내용",
                19,
                20,user,
                ContestStatus.PROCEEDING);

        File audioFile = new File(1,"파일",true,user);
        UserPlay userPlay = new UserPlay(0,contest,"제 연주입니다",audioFile,user);
        UserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayCreateValidator validator = new UserPlayCreateValidatorImpl();
        //when then
//        Assertions.assertThatThrownBy(()->validator.validate(userPlay)).isInstanceOf()

        Assertions.assertThatNoException().isThrownBy(()-> validator.validate(userPlay));



    }
    @Test
    public void 대회상태가_예정이면_예외를_던진다(){

        //given
        User user = new User(
                1,
                "test@gmail.com",
                "테스트닉네임",
                "패스워드",
                10,
                20
        );
        Contest contest = new Contest(
                1,
                "대회",
                "대회내용",
                19,
                20,user,
                ContestStatus.PENDING);

        File audioFile = new File(1,"파일",true,user);
        UserPlay userPlay = new UserPlay(0,contest,"제 연주입니다",audioFile,user);
        UserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayCreateValidator validator = new UserPlayCreateValidatorImpl();
        //when then
        Assertions.assertThatThrownBy(()->validator.validate(userPlay)).isInstanceOf(InvalidContestException.class);



    }

    @Test
    public void 대회상태가_대회제출마감_이면_예외를_던진다(){

        //given
        User user = new User(
                1,
                "test@gmail.com",
                "테스트닉네임",
                "패스워드",
                10,
                20
        );
        Contest contest = new Contest(
                1,
                "대회",
                "대회내용",
                19,
                20,user,
                ContestStatus.REGISTER_END);

        File audioFile = new File(1,"파일",true,user);
        UserPlay userPlay = new UserPlay(0,contest,"제 연주입니다",audioFile,user);
        UserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayCreateValidator validator = new UserPlayCreateValidatorImpl();
        //when then
        Assertions.assertThatThrownBy(()->validator.validate(userPlay)).isInstanceOf(InvalidContestException.class);

    }

    @Test
    public void 대회상태가_최종마감_이면_예외를_던진다(){

        //given
        User user = new User(
                1,
                "test@gmail.com",
                "테스트닉네임",
                "패스워드",
                10,
                20
        );
        Contest contest = new Contest(
                1,
                "대회",
                "대회내용",
                19,
                20,user,
                ContestStatus.REGISTER_END);

        File audioFile = new File(1,"파일",true,user);
        UserPlay userPlay = new UserPlay(0,contest,"제 연주입니다",audioFile,user);
        UserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayCreateValidator validator = new UserPlayCreateValidatorImpl();
        //when then
        Assertions.assertThatThrownBy(()->validator.validate(userPlay)).isInstanceOf(InvalidContestException.class);
    }






}