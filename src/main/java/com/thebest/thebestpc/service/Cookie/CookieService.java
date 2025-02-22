package com.thebest.thebestpc.service.Cookie;


import jakarta.servlet.http.Cookie;

import java.util.List;

public interface CookieService {

    void addCookie(Cookie cookie);

    void deleteCookie(String name);

    Cookie getCookie(String name);

    void addCookie(String key, String value);

    void addCookie(String key, String value, int maxAge);

    void removeCookie(String key);

    void removeAllCookies();

    void addCookieJson(String key, Object value);

    <T> List<T> getCookiesFromJson(String key , Class<T> clazz);

}
