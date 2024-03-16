package com.project.contestapplication.userplay.service;

import com.project.contestapplication.contest.domain.Contest;
import com.project.contestapplication.contest.domain.ContestStatus;
import com.project.contestapplication.storage.domain.File;
import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;
import com.project.contestapplication.userplay.exception.MultipleUserPlayException;
import com.project.contestapplication.userplay.repository.FakeUserPlayRepository;
import com.project.contestapplication.userplay.repository.UserPlayRepository;
import com.project.contestapplication.userplay.service.validator.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

class UserPlayManagerImplTest {



    @Test
    public void 정상적인_요청에_대해_글쓰기가_진행된다(){

        //given
        UserPlayCreateValidator userPlayCreateValidator = new FakePlayCreateValidator();

        UserPlayUpdateValidator userPlayUpdateValidator = new FakePlayUpdateValidator();
        UserPlayDeleteValidator userPlayDeleteValidator = new FakePlayDeleteValidator();
        FakeUserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayManager userPlayManager = new UserPlayManagerImpl(
                userPlayRepository,
                userPlayCreateValidator,
                userPlayUpdateValidator,
                userPlayDeleteValidator
        );
        User user = new User(2,"test@gmail.com"," 테스트 닉네임","패스워드",10,20);
        Contest contest = new Contest(3,"테스트 대회","테스트 내용",10,20,user, ContestStatus.PROCEEDING);
        File file = new File(3,"파일명",true,user);
        UserPlay userPlay = new UserPlay(0,contest,"타이틀",file,user);
        UserPlay savedUserPlay = new UserPlay(1,contest,"타이틀",file,user);
        //when
        userPlayManager.createUserPlayOn(userPlay);
        //then
        Assertions.assertThat(userPlayRepository.id).isEqualTo(1);
        Assertions.assertThat(userPlayRepository.list.get(0)).isEqualTo(savedUserPlay);

    }

    @Test
    public void createValidator_호출_여부를_확인한다(){
        //given
        FakePlayCreateValidator userPlayCreateValidator = new FakePlayCreateValidator();

        UserPlayUpdateValidator userPlayUpdateValidator = new FakePlayUpdateValidator();
        UserPlayDeleteValidator userPlayDeleteValidator = new FakePlayDeleteValidator();
        FakeUserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayManager userPlayManager = new UserPlayManagerImpl(
                userPlayRepository,
                userPlayCreateValidator,
                userPlayUpdateValidator,
                userPlayDeleteValidator
        );
        User user = new User(2,"test@gmail.com"," 테스트 닉네임","패스워드",10,20);
        Contest contest = new Contest(3,"테스트 대회","테스트 내용",10,20,user, ContestStatus.PROCEEDING);
        File file = new File(3,"파일명",true,user);
        UserPlay userPlay = new UserPlay(0,contest,"타이틀",file,user);
        //when
        userPlayManager.createUserPlayOn(userPlay);
        //then
        Assertions.assertThat(userPlayCreateValidator.callCount).isEqualTo(1);
    }

    @Test
    public void updateValidator_호출_여부를_확인한다(){
        //given
        FakePlayCreateValidator userPlayCreateValidator = new FakePlayCreateValidator();

        FakePlayUpdateValidator userPlayUpdateValidator = new FakePlayUpdateValidator();
        UserPlayDeleteValidator userPlayDeleteValidator = new FakePlayDeleteValidator();
        FakeUserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayManager userPlayManager = new UserPlayManagerImpl(
                userPlayRepository,
                userPlayCreateValidator,
                userPlayUpdateValidator,
                userPlayDeleteValidator
        );
        User user = new User(2,"test@gmail.com"," 테스트 닉네임","패스워드",10,20);
        Contest contest = new Contest(3,"테스트 대회","테스트 내용",10,20,user, ContestStatus.PROCEEDING);
        File file = new File(3,"파일명",true,user);
        UserPlay userPlay = new UserPlay(0,contest,"타이틀",file,user);
        UserPlay savedUserPlay = new UserPlay(2,contest,"타이틀",file,user);
        userPlayRepository.save(savedUserPlay);
        //when
        userPlayManager.updateUserPlay(userPlay);
        //then
        Assertions.assertThat(userPlayUpdateValidator.callCount).isEqualTo(1);

    }

    @Test
    public void deleteValidator_호출_여부를_확인한다(){

        //given
        FakePlayCreateValidator userPlayCreateValidator = new FakePlayCreateValidator();

        FakePlayUpdateValidator userPlayUpdateValidator = new FakePlayUpdateValidator();
        FakePlayDeleteValidator userPlayDeleteValidator = new FakePlayDeleteValidator();

        FakeUserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayManager userPlayManager = new UserPlayManagerImpl(
                userPlayRepository,
                userPlayCreateValidator,
                userPlayUpdateValidator,
                userPlayDeleteValidator
        );
        User user = new User(2,"test@gmail.com"," 테스트 닉네임","패스워드",10,20);
        Contest contest = new Contest(3,"테스트 대회","테스트 내용",10,20,user, ContestStatus.PROCEEDING);
        File file = new File(3,"파일명",true,user);
        UserPlay savedUserPlay = new UserPlay(2,contest,"타이틀",file,user);
        userPlayRepository.save(savedUserPlay);
        //when
        userPlayManager.deleteUserPlay(2,user);
        //then
        Assertions.assertThat(userPlayDeleteValidator.callCount).isEqualTo(1);


    }
    @Test
    public void 정상적인_요청에_대해_read_된다(){

        //given
        FakePlayCreateValidator userPlayCreateValidator = new FakePlayCreateValidator();

        FakePlayUpdateValidator userPlayUpdateValidator = new FakePlayUpdateValidator();
        FakePlayDeleteValidator userPlayDeleteValidator = new FakePlayDeleteValidator();

        FakeUserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayManager userPlayManager = new UserPlayManagerImpl(
                userPlayRepository,
                userPlayCreateValidator,
                userPlayUpdateValidator,
                userPlayDeleteValidator
        );
        User user = new User(2,"test@gmail.com"," 테스트 닉네임","패스워드",10,20);
        Contest contest = new Contest(3,"테스트 대회","테스트 내용",10,20,user, ContestStatus.PROCEEDING);
        File file = new File(3,"파일명",true,user);
        UserPlay savedUserPlay = new UserPlay(2,contest,"타이틀",file,user);
        userPlayRepository.save(savedUserPlay);
        //when
        UserPlay userPlay = userPlayManager.readUserPlay(2);
        //then
        Assertions.assertThat(userPlay).isEqualTo(savedUserPlay);

    }
    @Test
    public void 정상적인_요청에_대해_update_된다(){

        //given
        FakePlayCreateValidator userPlayCreateValidator = new FakePlayCreateValidator();

        FakePlayUpdateValidator userPlayUpdateValidator = new FakePlayUpdateValidator();
        FakePlayDeleteValidator userPlayDeleteValidator = new FakePlayDeleteValidator();

        FakeUserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayManager userPlayManager = new UserPlayManagerImpl(
                userPlayRepository,
                userPlayCreateValidator,
                userPlayUpdateValidator,
                userPlayDeleteValidator
        );
        User user = new User(2,"test@gmail.com"," 테스트 닉네임","패스워드",10,20);
        Contest contest = new Contest(3,"테스트 대회","테스트 내용",10,20,user, ContestStatus.PROCEEDING);
        File savedFile = new File(3,"파일명",true,user);
        File file = new File(4,"파일",true,user);
        UserPlay savedUserPlay = new UserPlay(2,contest,"타이틀",savedFile,user);

        userPlayRepository.save(savedUserPlay);
        //when
        UserPlay userPlay = new UserPlay(2,contest,"수정입니다",file,user);
        UserPlay updateUserPlay = userPlayManager.updateUserPlay(userPlay);
        //then
        Assertions.assertThat(updateUserPlay).isEqualTo(userPlay);
        Assertions.assertThat(userPlayRepository.list.size()).isEqualTo(1);
        Assertions.assertThat(userPlayRepository.list.get(0)).isEqualTo(userPlay);

    }
    @Test
    public void 정상적인_요청에_대해_delete_된다(){

        //given
        FakePlayCreateValidator userPlayCreateValidator = new FakePlayCreateValidator();

        FakePlayUpdateValidator userPlayUpdateValidator = new FakePlayUpdateValidator();
        FakePlayDeleteValidator userPlayDeleteValidator = new FakePlayDeleteValidator();

        FakeUserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayManager userPlayManager = new UserPlayManagerImpl(
                userPlayRepository,
                userPlayCreateValidator,
                userPlayUpdateValidator,
                userPlayDeleteValidator
        );
        User user = new User(2,"test@gmail.com"," 테스트 닉네임","패스워드",10,20);
        Contest contest = new Contest(3,"테스트 대회","테스트 내용",10,20,user, ContestStatus.PROCEEDING);
        File savedFile = new File(3,"파일명",true,user);
        File file = new File(4,"파일",true,user);
        UserPlay savedUserPlay = new UserPlay(2,contest,"타이틀",savedFile,user);

        userPlayRepository.save(savedUserPlay);
        //when
        UserPlay userPlay = new UserPlay(2,contest,"수정입니다",file,user);
        userPlayManager.deleteUserPlay(2,user);
        //then
        Assertions.assertThat(userPlayRepository.list.size()).isEqualTo(0);
    }

    @Test
    public void 하나_이상의_UserPlay를_등록하려고_하면_예외를_던진다(){

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

        File audioFile = new File(0,"파일",true,user);
        File savedAudioFile = new File(0,"파일",true,user);
        UserPlay userPlay = new UserPlay(0,contest,"제 연주입니다",audioFile,user);
        UserPlay savedUserPlay = new UserPlay(0,contest,"제 연주입니다",savedAudioFile,user);

        FakePlayCreateValidator userPlayCreateValidator = new FakePlayCreateValidator();

        FakePlayUpdateValidator userPlayUpdateValidator = new FakePlayUpdateValidator();
        FakePlayDeleteValidator userPlayDeleteValidator = new FakePlayDeleteValidator();

        FakeUserPlayRepository userPlayRepository = new FakeUserPlayRepository();
        UserPlayManager userPlayManager = new UserPlayManagerImpl(
                userPlayRepository,
                userPlayCreateValidator,
                userPlayUpdateValidator,
                userPlayDeleteValidator
        );
        userPlayRepository.save(savedUserPlay);
        //when then
        Assertions.assertThatThrownBy(()-> userPlayManager.createUserPlayOn(userPlay)).isInstanceOf(MultipleUserPlayException.class);


    }



}