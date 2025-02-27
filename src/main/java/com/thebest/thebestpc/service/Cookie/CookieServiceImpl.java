package com.thebest.thebestpc.service.Cookie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thebest.thebestpc.service.json.JsonService;
import com.thebest.thebestpc.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {

    HttpServletRequest request;
    HttpServletResponse response;
    ObjectMapper objectMapper;
    JsonService jsonService;

    @Override
    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    @Override
    public void deleteCookie(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    public Cookie getCookie(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    @Override
    public void addCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void addCookie(String key, String value, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    public void removeCookie(String key) {
        Cookie cookie = new Cookie(key, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }


    @Override
    public void removeAllCookies() {
    }

    @Override
    public void addCookieJson(String key, Object value) {
        String json = jsonService.toJson(value);
        if (json == null) {
            return;
        }
        Cookie cookie = new Cookie(key, CookieUtil.encode(json));
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }


    @Override
    public <T> List<T> getCookiesFromJson(String key, Class<T> clazz) {
        return Optional.ofNullable(request.getCookies())
                .map(cookies -> {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals(key)) {

                            return jsonService.parseJsonArray(CookieUtil.decodeValue(cookie.getValue()), clazz);
                        }
                    }
                    return new ArrayList<T>();
                })
                .orElseGet(ArrayList::new);
    }

}
