package com.thebest.thebestpc.service.CookieService;


import jakarta.servlet.http.Cookie;

public interface CookieService {

    void addCookie(Cookie cookie);

    void deleteCookie(String name);

    Cookie getCookie(String name);
}
