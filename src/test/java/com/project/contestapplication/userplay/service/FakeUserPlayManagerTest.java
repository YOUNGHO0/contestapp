package com.project.contestapplication.userplay.service;

import com.project.contestapplication.user.domain.User;
import com.project.contestapplication.userplay.domain.UserPlay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakeUserPlayManagerTest {
    @Test
    public void create_호출시_callCount가_1이_증가한다(){
        FakeUserPlayManager fakeUserPlayManager = new FakeUserPlayManager();
        fakeUserPlayManager.createUserPlayOn(new UserPlay(0,null,null,null,null));
        Assertions.assertThat(fakeUserPlayManager.callCount).isEqualTo(1);

    }
    @Test
    public void read_호출시_callCount가_1이_증가한다(){
        FakeUserPlayManager fakeUserPlayManager = new FakeUserPlayManager();
        fakeUserPlayManager.readUserPlay(0);
        Assertions.assertThat(fakeUserPlayManager.callCount).isEqualTo(1);

    }

    @Test
    public void update_호출시_callCount가_1이_증가한다(){
        FakeUserPlayManager fakeUserPlayManager = new FakeUserPlayManager();
        fakeUserPlayManager.updateUserPlay(new UserPlay(0,null,null,null,null));
        Assertions.assertThat(fakeUserPlayManager.callCount).isEqualTo(1);

    }

    @Test
    public void delete_호출시_callCount가_1이_증가한다(){
        FakeUserPlayManager fakeUserPlayManager = new FakeUserPlayManager();
        fakeUserPlayManager.deleteUserPlay(0,new User(0,null,null,null,0,0));
        Assertions.assertThat(fakeUserPlayManager.callCount).isEqualTo(1);

    }


}