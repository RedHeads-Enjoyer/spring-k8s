package com.example.demo.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Entity
@Table(name = "app_user")
public class User implements UserDetails, OAuth2User {

    @Id
    private String name;
    private String token;

    public User() {}

    public User(String name, String token) {
        this.name = name;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null; // Пароль не нужен для OAuth2
    }

    @Override
    public String getUsername() {
        return name; // Используем email как имя пользователя
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.emptyMap();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {return token;}
}
