package com.jsoft.springsecurity.security;

import com.jsoft.springsecurity.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class MyUserDetailService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    private String userName = "admin";
    private String password = "123";


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        log.info(" 登录用户名:{} ", name);


        // 这里查询数据库获取用户名/密码，并生产token
        if(Objects.equals(name, userName)) {
            String encodePwd = passwordEncoder.encode(password);
            MyUserDetail userDetail = new MyUserDetail();
            userDetail.setUsername(name);
            userDetail.setPassword(encodePwd);
            userDetail.setToken(JwtUtil.createJWT(name, name));
            // 根据用户名获取用户基础信息 && 用户的角色信息
            List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
            for (int i = 0; i < 1; i++) {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("role_" + i);
                grantedAuthorityList.add(simpleGrantedAuthority);
            }
            userDetail.setGrantedAuthorityList(grantedAuthorityList);
            return userDetail;
        }

        return null;
    }


}
