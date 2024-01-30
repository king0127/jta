package com.jsoft.springsecurity.security;

import com.jsoft.springsecurity.common.BaseResult;
import com.jsoft.springsecurity.common.EnumCode;
import com.jsoft.springsecurity.utils.RespUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class FailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.info(" 认证失败，请核对信息------------ ");
        RespUtil.out(response, BaseResult.failure(EnumCode.FAILURE));
    }
}
