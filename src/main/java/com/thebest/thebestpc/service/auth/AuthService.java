package com.thebest.thebestpc.service.auth;

import com.thebest.thebestpc.config.custom.TokenInfo;

public interface AuthService {

    void addTokenToCookie(String token);

    String getTokenFromCookie();

    void removeTokenFromCookie();

    String generateAccessToken(TokenInfo tokenInfo);
}
