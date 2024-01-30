package com.sdk.oauth.web;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class UserDetailDo implements UserDetails {


    private String username;

    private String password;

    private List<GrantedAuthority> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return password;
    }

    // 账号是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 认证是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账号是否停用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
