package com.ccalendar.server.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MySuccessAuthHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final static String MESSAGE = "{ \"data\" : \"%s\", \"meta\" : { \"message\" : \"Authorized\", \"status\" : \"OK\" } }";

    @Override
    public void onAuthenticationSuccess(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication auth) throws ServletException, IOException {

        request.getSession().setMaxInactiveInterval(7 * 24 * 60 * 60); //One week session

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");
        response.getOutputStream().print(String.format(MESSAGE, request.getSession().getId()));

        clearAuthenticationAttributes(request);
    }
}
