package com.jsoft.springsecurity.security;

import com.jsoft.springsecurity.common.BaseResult;
import com.jsoft.springsecurity.utils.RespUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
        RespUtil.out(response, BaseResult.success());
        log.info(" 认证成功------------ ");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info(" 认证成功------------111111 ");
        MyUserDetail userDetail = (MyUserDetail) authentication.getPrincipal();
        RespUtil.out(response, BaseResult.success(userDetail));
    }
}
