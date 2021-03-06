package com.ccalendar.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MySuccessLogoutHandler implements LogoutSuccessHandler {
    private final static String MESSAGE = "{ \"data\" : null, \"meta\" : { \"message\" : \"Success logout\", \"status\" : \"OK\" } }";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream().print(MESSAGE);

    }
}
