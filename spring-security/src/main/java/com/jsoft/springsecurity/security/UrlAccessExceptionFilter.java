package com.jsoft.springsecurity.security;

import com.jsoft.springsecurity.common.BaseResult;
import com.jsoft.springsecurity.common.EnumCode;
import com.jsoft.springsecurity.utils.RespUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UrlAccessExceptionFilter implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        RespUtil.out(response, BaseResult.failure(EnumCode.UNAUTHORIZED));
    }
}
