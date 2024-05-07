package com.study.blog.business.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails{
    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(user.getRole());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료가 아닌지 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 락이 아닌지 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 자격 증명 만료가 아닌지 여부
    }

    @Override
    public boolean isEnabled() {
        return user.isStatus(); // 계정 활성화 여부 반환
    }

    public User getUser() {
        return user;
    }
}
