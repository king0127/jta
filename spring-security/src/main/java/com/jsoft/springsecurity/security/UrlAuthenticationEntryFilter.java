package com.jsoft.springsecurity.security;

import com.jsoft.springsecurity.common.BaseResult;
import com.jsoft.springsecurity.common.EnumCode;
import com.jsoft.springsecurity.utils.RespUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UrlAuthenticationEntryFilter implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        RespUtil.out(response, BaseResult.failure(EnumCode.UNAUTHORIZED));
    }
}
