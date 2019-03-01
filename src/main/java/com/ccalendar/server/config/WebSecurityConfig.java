package com.ccalendar.server.config;

import com.ccalendar.server.domain.services.user.UserService;
import com.ccalendar.server.security.MyFailureAuthHandler;
import com.ccalendar.server.security.MySuccessAuthHandler;
import com.ccalendar.server.security.MySuccessLogoutHandler;
import com.ccalendar.server.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private MySuccessAuthHandler mySuccessHandler;
    private MyFailureAuthHandler myFailureHandler;
    private MySuccessLogoutHandler mySuccessLogoutHandler;
    private UserService userService;

    public WebSecurityConfig(){
        super();
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Настройка Spring Security
        http
            .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()
                .authorizeRequests()
                .antMatchers("/register", "/regions").permitAll()
                .anyRequest().authenticated()
            .and()
                .requestCache()
                .requestCache(new NullRequestCache())
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true)
                    .sessionRegistry(new SessionRegistryImpl())
                .and()
            .and()
                .formLogin()
                .usernameParameter("login")
                .passwordParameter("password")
                .successHandler(mySuccessHandler)
                .failureHandler(myFailureHandler)
            .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(mySuccessLogoutHandler);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
        //Настройка способа авторизации
        //Установка собственного сервиса пользователея
        auth.userDetailsService(userService)
                .passwordEncoder(encoder());
    }

    //Шифратор для паролей
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Autowired
    public void setMySuccessHandler(MySuccessAuthHandler mySuccessHandler){
        this.mySuccessHandler = mySuccessHandler;
    }

    @Autowired
    public void setMyFailureHandler(MyFailureAuthHandler myFailureHandler) {
        this.myFailureHandler = myFailureHandler;
    }

    @Autowired
    public void setMySuccessLogoutHandler(MySuccessLogoutHandler mySuccessLogoutHandler) {
        this.mySuccessLogoutHandler = mySuccessLogoutHandler;
    }

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }
}
