package com.thebest.thebestpc.service.jwt;

import com.thebest.thebestpc.config.custom.TokenInfo;
import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken(TokenInfo tokenInfo, long expiration);

    String getUsername(String token);

    boolean validateToken(String token);

    boolean verifyUsername(String token, String username);

    long getExpiration(String token);

    Claims extractAllClaims(String token);

    String getUsername(Claims claims);

    String getRole(Claims claims);

    long getExpiration(Claims claims);

    TokenInfo getTokenInfo(String token);
}
