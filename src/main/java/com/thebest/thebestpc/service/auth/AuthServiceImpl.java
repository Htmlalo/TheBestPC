package com.thebest.thebestpc.service.auth;

import com.thebest.thebestpc.config.custom.TokenInfo;
import com.thebest.thebestpc.service.Cookie.CookieService;
import com.thebest.thebestpc.service.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    CookieService cookieService;
    JwtService jwtService;

    @Override
    public void addTokenToCookie(String token) {
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookieService.addCookie(cookie);
    }

    @Override
    public String getTokenFromCookie() {
        return cookieService.getCookie("token").getValue();
    }

    @Override
    public void removeTokenFromCookie() {
        cookieService.deleteCookie("token");
    }

    @Override
    public String generateAccessToken(TokenInfo tokenInfo) {
        return jwtService.generateToken(tokenInfo, TimeUnit.HOURS.toMillis(1));
    }
}
