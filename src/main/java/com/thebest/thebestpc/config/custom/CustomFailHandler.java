package com.thebest.thebestpc.config.custom;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class CustomFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error(exception.getMessage());
        log.error(exception.getClass().getName());

        int error = 0;
        switch (exception) {
            case UsernameNotFoundException ignored -> error = -1;
            case BadCredentialsException ignored -> error = 1;
            case DisabledException ignored -> error = -2;
            default -> {
            }
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("/login").forward(request, response);
    }
}
