package com.project.contestapplication.userplay.service;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPlayServiceImplTest {

    @Test
    void UserPlayManger_create를_올바르게_호출하는지_검증한다() {
        //given
        FakeUserPlayManager userPlayManager = new FakeUserPlayManager();
        UserPlayService userPlayService = new UserPlayServiceImpl(userPlayManager);
        //when
        userPlayService.create(new UserPlay(0,null,null,null,null));
        //then
        Assertions.assertThat(userPlayManager.callCount).isEqualTo(1);


    }

    @Test
    void UserPlayManage_read를_올바르게_호출하는지_검증한다() {
        //given
        FakeUserPlayManager userPlayManager = new FakeUserPlayManager();
        UserPlayService userPlayService = new UserPlayServiceImpl(userPlayManager);
        //when
        userPlayService.read(0);
        //then
        Assertions.assertThat(userPlayManager.callCount).isEqualTo(1);
    }

    @Test
    void UserPlayManage_update를_올바르게_호출하는지_검증한다() {
        //given
        FakeUserPlayManager userPlayManager = new FakeUserPlayManager();
        UserPlayService userPlayService = new UserPlayServiceImpl(userPlayManager);
        //when
        userPlayService.update(new UserPlay(0,null,null,null,null));
        //then
        Assertions.assertThat(userPlayManager.callCount).isEqualTo(1);
    }

    @Test
    void UserPlayManage_delete를_올바르게_호출하는지_검증한다() {
        //given
        FakeUserPlayManager userPlayManager = new FakeUserPlayManager();
        UserPlayService userPlayService = new UserPlayServiceImpl(userPlayManager);
        //when
        userPlayService.delete(0,new User(0,null,null,null,0,0));
        //then
        Assertions.assertThat(userPlayManager.callCount).isEqualTo(1);
    }
}