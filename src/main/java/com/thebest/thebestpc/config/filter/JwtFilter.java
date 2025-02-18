package com.thebest.thebestpc.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request
            , @NotNull HttpServletResponse response
            , @NotNull FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (uri.contains("/TheBestPc/login") || uri.contains("/TheBestPc/register")) {
            filterChain.doFilter(request, response);
            return;
        }
    }
}
