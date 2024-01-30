package com.jsoft.springsecurity.config;

import com.jsoft.springsecurity.security.FailureHandler;
import com.jsoft.springsecurity.security.GrantUserNamePasswordFilter;
import com.jsoft.springsecurity.security.JwtAuthenticationFilter;
import com.jsoft.springsecurity.security.MyUserDetailService;
import com.jsoft.springsecurity.security.SuccessHandler;
import com.jsoft.springsecurity.security.UrlAccessExceptionFilter;
import com.jsoft.springsecurity.security.UrlAuthenticationEntryFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyUserDetailService userDetailService;
    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Resource
    private SuccessHandler successHandler;
    @Resource
    private FailureHandler failureHandler;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UrlAccessExceptionFilter urlAccessExceptionFilter;
    @Resource
    private UrlAuthenticationEntryFilter urlAuthenticationEntryFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public GrantUserNamePasswordFilter userNamePasswordFilter() throws Exception {
        GrantUserNamePasswordFilter filter = new GrantUserNamePasswordFilter(successHandler, failureHandler);
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 建造者模式入口责任链路的创建，
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/query/info").hasAnyRole("role_1")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                // 这里设置的成功/失败的handler不对的，这里只是前端页面转发的地址
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
        ;
        http.exceptionHandling().authenticationEntryPoint(urlAuthenticationEntryFilter);
        http.exceptionHandling().accessDeniedHandler(urlAccessExceptionFilter);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterAt(userNamePasswordFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                ;
    }

}
