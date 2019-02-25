package com.ccalendar.server.config;

import com.ccalendar.server.domain.services.user.UserService;
import com.ccalendar.server.security.MySuccessAuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private MySuccessAuthHandler mySuccessHandler;
    private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();
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
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            .and()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .usernameParameter("login")
                .passwordParameter("password")
                .successHandler(mySuccessHandler)
                .failureHandler(myFailureHandler)
            .and()
                .rememberMe()
                .alwaysRemember(true)
            .and()
                .logout();
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
    public void setMySuccessHandler(MySuccessAuthHandler mySuccessHandler){
        this.mySuccessHandler = mySuccessHandler;
    }

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }
}
