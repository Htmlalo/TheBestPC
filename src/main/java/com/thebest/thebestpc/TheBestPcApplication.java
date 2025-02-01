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
        openBrowser("http://localhost:9090/TheBestPc/");
    }


    private static void openBrowser(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime runtime = Runtime.getRuntime();
        try {
            if (os.contains("win")) { // Windows
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) { // macOS
                runtime.exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) { // Linux/Unix
                runtime.exec("xdg-open " + url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


