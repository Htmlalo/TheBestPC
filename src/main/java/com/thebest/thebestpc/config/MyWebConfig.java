package com.thebest.thebestpc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thebest.thebestpc.model.Users;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.simplejavamail.api.email.Email;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration

public class MyWebConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return new ModelMapper();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    private void updateSecurityContext(Users updatedUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Tạo một Authentication mới với thông tin đã cập nhật
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                updatedUser,
                authentication.getCredentials(),
                authentication.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

}
