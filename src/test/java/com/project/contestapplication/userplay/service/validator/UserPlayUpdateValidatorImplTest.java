package com.project.contestapplication.userplay.service.validator;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.contest.exception.NoSameUserException;
import com.project.contestapplication.contest.exception.UnchangeableException;
import com.project.contestapplication.storage.domain.File;
import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserPlayUpdateValidatorImplTest {

    @Test
    public void 수정할_userPlay가_없으면_예외를_던진다(){

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
                ContestStatus.FINAL_END);

        File audioFile = new File(
                4,
                "파일",
                true,
                user
        );

        UserPlay userPlay = new UserPlay(
                1,
                contest,
                "연주 수정입니다",
                audioFile,
                user
        );


        //when
        UserPlayUpdateValidator validator = new UserPlayUpdateValidatorImpl();
        // then
        Assertions.assertThatThrownBy(()->validator.validate(userPlay,null))
                .isInstanceOf(RuntimeException.class);


    }
    @Test
    public void 대회진행상태가_진행중이면_검증기를_통과한다(){
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

        File audioFile = new File(4,"파일",true,user);
        File savedAudioFile = new File(5,"파일",true,user);
        UserPlay userPlay = new UserPlay(1,contest,"연주 수정입니다",audioFile,user);
        UserPlay savedUserPlay = new UserPlay(0,contest,"제 연주입니다",savedAudioFile,user);

        //when
        UserPlayUpdateValidator validator = new UserPlayUpdateValidatorImpl();
        // then
           Assertions.assertThatNoException().isThrownBy(()->validator.validate(userPlay,savedUserPlay));


    }
    @Test
    public void 대회진행상태가_예정이면_예외를_던진다(){

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

        File audioFile = new File(
                4,
                "파일",
                true,
                user
        );
        File savedAudioFile = new File(
                5,
                "파일",
                true,
                user
        );
        UserPlay userPlay = new UserPlay(
                1,
                contest,
                "연주 수정입니다",
                audioFile,
                user
        );
        UserPlay savedUserPlay = new UserPlay(
                0,
                contest,
                "제 연주입니다",
                savedAudioFile,
                user
        );
        //when
        UserPlayUpdateValidator validator = new UserPlayUpdateValidatorImpl();
        // then
        Assertions.assertThatThrownBy(()->validator.validate(userPlay,savedUserPlay))
                .isInstanceOf(UnchangeableException.class);


    }
    @Test
    public void 대회진행상태가_대회제출마감이면_예외를_던진다(){

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

        File audioFile = new File(
                4,
                "파일",
                true,
                user
        );
        File savedAudioFile = new File(
                5,
                "파일",
                true,
                user
        );
        UserPlay userPlay = new UserPlay(
                1,
                contest,
                "연주 수정입니다",
                audioFile,
                user
        );
        UserPlay savedUserPlay = new UserPlay(
                0,
                contest,
                "제 연주입니다",
                savedAudioFile,
                user
        );

        //when
        UserPlayUpdateValidator validator = new UserPlayUpdateValidatorImpl();
        // then
        Assertions.assertThatThrownBy(()->validator.validate(userPlay,savedUserPlay))
                .isInstanceOf(UnchangeableException.class);

    }
    @Test
    public void 대회진행상태가_최종마감이면_예외를_던진다(){

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
                ContestStatus.FINAL_END);

        File audioFile = new File(
                4,
                "파일",
                true,
                user
        );
        File savedAudioFile = new File(
                5,
                "파일",
                true,
                user
        );
        UserPlay userPlay = new UserPlay(
                1,
                contest,
                "연주 수정입니다",
                audioFile,
                user
        );
        UserPlay savedUserPlay = new UserPlay(
                0,
                contest,
                "제 연주입니다",
                savedAudioFile,
                user
        );
        //when
        UserPlayUpdateValidator validator = new UserPlayUpdateValidatorImpl();
        // then
        Assertions.assertThatThrownBy(()->validator.validate(userPlay,savedUserPlay))
                .isInstanceOf(UnchangeableException.class);

    }

    @Test
    public void 글_작성자가_아니면_예외를_던진다(){

        //given
        User user = new User(
                1,
                "test@gmail.com",
                "테스트닉네임",
                "패스워드",
                10,
                20
        );

        User savedUser = new User(
                3,
                "t22est@gmail.com",
                "다른사람",
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
                ContestStatus.FINAL_END);

        File audioFile = new File(
                4,
                "파일",
                true,
                user
        );
        File savedAudioFile = new File(
                5,
                "파일",
                true,
                user
        );
        UserPlay userPlay = new UserPlay(
                1,
                contest,
                "연주 수정입니다",
                audioFile,
                user
        );
        UserPlay savedUserPlay = new UserPlay(
                0,
                contest,
                "제 연주입니다",
                savedAudioFile,
                savedUser
        );
        //when
        UserPlayUpdateValidator validator = new UserPlayUpdateValidatorImpl();
        // then
        Assertions.assertThatThrownBy(()->validator.validate(userPlay,savedUserPlay))
                .isInstanceOf(NoSameUserException.class);

    }



}