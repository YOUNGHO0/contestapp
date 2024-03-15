package com.project.contestapplication.user.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Getter
public class User {
    long id;
    String email;
    String nickName;
    String password;
    long registeredAt;
    long lastLoggedAt;

    @Builder
    public User(long id,String email,String nickName,String password, long registeredAt, long lastLoggedAt){
        this.id = id;
        this.email = email;
        this.nickName =nickName;
        this.password =password;
        this.registeredAt =registeredAt;
        this.lastLoggedAt =lastLoggedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        User user = (User) obj;
        return( this.id == user.getId() &&
                this.email.equals(user.email));

    }

    @Override
    public int hashCode() {
        return Objects.hash(id,email);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> "ROLE_USER");
        return authorities;
    }
}
