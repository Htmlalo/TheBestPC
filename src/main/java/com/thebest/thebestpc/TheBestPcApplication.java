package com.thebest.thebestpc;

import com.thebest.thebestpc.dto.UserCreateDto;
import com.thebest.thebestpc.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class TheBestPcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheBestPcApplication.class, args);

    }

}


