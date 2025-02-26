package com.thebest.thebestpc.config;

import com.thebest.thebestpc.config.custom.CustomFailHandler;
import com.thebest.thebestpc.config.custom.CustomUserDetailsService;
import com.thebest.thebestpc.config.handler.CustomSuccessHandler;
import com.thebest.thebestpc.service.users.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomSuccessHandler customSuccessHandler;

    public SecurityConfig(CustomSuccessHandler customSuccessHandler) {
        this.customSuccessHandler = customSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UsersService usersService, CustomUserDetailsService customUserDetailsService, CustomFailHandler customFailHandler) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));


        http.formLogin(login -> {
            login.loginPage("/login");
            login.loginProcessingUrl("/p/login");
            login.defaultSuccessUrl("/banner");
            login.failureHandler(customFailHandler);
            login.permitAll();
        });
        http.logout(logout -> {
            logout.logoutUrl("/logout");
            logout.clearAuthentication(true);
            logout.logoutSuccessUrl("/");
            logout.permitAll();
        });

        http.authorizeHttpRequests(auth -> {
//            auth.requestMatchers("/**").permitAll();
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/banner").permitAll();
            auth.requestMatchers("/detail/**").permitAll();
            auth.requestMatchers("/res/**").permitAll();
            auth.requestMatchers("/uploads/**").permitAll();
            auth.requestMatchers("/cart/**").permitAll();
            auth.anyRequest().authenticated();
        });

//        http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);

        return new ProviderManager(provider);
    }

    @Bean
    public UserDetailsService userDetailsService(UsersService usersService) {
        return new CustomUserDetailsService(usersService);
    }

}