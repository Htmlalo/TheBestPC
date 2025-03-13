package com.thebest.thebestpc.config.handler;

import com.thebest.thebestpc.model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (authentication.getPrincipal() instanceof Users user) {
                log.info(user.getUsername(),user.getEmail(),user.getPassword());

        }
    }
}
