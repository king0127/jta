package com.jsoft.springsecurity.security;

import com.jsoft.springsecurity.common.BaseConstant;
import com.jsoft.springsecurity.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String authToken = request.getHeader(BaseConstant.HEAD_AUTH);

        if(authToken != null && authToken != "") {
            authToken = authToken.replace("Bearer ", "");
            // 解析token
        }
        // 解析token
        try {
            String subject = JwtUtil.parseJWT(authToken);
            // 从token中获取用户信息
            // 根据用户信息查询用户详情
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            // 这里可以用null 也可以存放用户的密码： <这里设置为空，会再次走一遍密码比对流程>
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 更新上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(" 解析token失败 ");
            throw new ServletException("登录失败，请重新登录！");
        }
    }
}
