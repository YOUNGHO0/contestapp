package com.project.contestapplication.user.infrastructre;

import com.project.contestapplication.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserEntity {

    public static UserEntity from(User user){
        return new UserEntity(
                user.getId(),
                user.getNickName(),
                user.getEmail(),
                user.getPassword(),
                user.getRegisteredAt(),
                user.getLastLoggedAt());

    }
    public static User toUser(UserEntity userEntity){
        return new User(
                userEntity.id,
                userEntity.userId,
                userEntity.nickname,
                userEntity.password,
                userEntity.registeredAt,
                userEntity.lastLoggedAt);
    }

    @Id
    @GeneratedValue
    private long id;
    private String nickname;
    private String userId;
    private String password;
    private long registeredAt;
    private long lastLoggedAt;

    @Builder
    public UserEntity(long id,String nickname, String userId, String password,long registeredAt,long lastLoggedAt){
        this.id = id;
        this.nickname = nickname;
        this.userId = userId;
        this.password =password;
        this.registeredAt = registeredAt;
        this.lastLoggedAt = lastLoggedAt;
    }

}
