package com.project.contestapplication.user.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class UserTest {

    @Test
    public void 같은_PK와_ID면_true를_반환한다(){
        String email ="testEmail.@gmail.com";
        String nickName = "nickName";
        String password = "password";
        User user1 = new User(1,email,nickName,password,1,13);
        User user2 = new User(1,email,nickName,password,12,1);


        Assertions.assertThat(user1).isEqualTo(user2);

    }
    @Test
    public void PK가_다르면_false를_리턴한다(){

        String email ="testEmail.@gmail.com";
        String nickName = "nickName";
        String password = "password";
        User user1 = new User(2,email,nickName,password,1,13);
        User user2 = new User(1,email,nickName,password,12,1);

        Assertions.assertThat(user1).isNotEqualTo(user2);

    }
    @Test
    public void 이메일이_다르면_false를_리턴한다(){
        String email ="testEmail.@gmail.com";
        String nickName = "nickName";
        String password = "password";
        User user1 = new User(2,email,nickName,password,1,13);
        User user2 = new User(2,"notEqual",nickName,password,12,1);

        Assertions.assertThat(user1).isNotEqualTo(user2);
    }

    @Test
    public void hashCode가_올바르게_동작하는지_확인한다(){

        Map<User,Integer> userMap = new HashMap<>();
        String email ="testEmail.@gmail.com";
        String nickName = "nickName";
        String password = "password";
        User user1 = new User(2,email,nickName,password,1,13);
        User user2 = new User(2,email,nickName,password,1,13);
        //given

        //when
        userMap.put(user1,userMap.getOrDefault(user1,0)+1);

        //then
        Assertions.assertThat(userMap.containsKey(user2)).isTrue();
        Assertions.assertThat(userMap.get(user2)).isEqualTo(1);
        userMap.remove(user2);
        Assertions.assertThat(userMap.containsKey(user1)).isFalse();

    }


}