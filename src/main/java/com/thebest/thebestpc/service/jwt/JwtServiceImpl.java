package com.thebest.thebestpc.service.jwt;

import com.nimbusds.jose.crypto.impl.MACProvider;
import com.thebest.thebestpc.config.custom.TokenInfo;
import com.thebest.thebestpc.util.KeyLoader;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private static final PublicKey publicKey = KeyLoader.loadPublicKey("public_key.pem");

    private static final PrivateKey privateKey = KeyLoader.loadPrivateKey("private_key.pem");


    @Override
    public String generateToken(TokenInfo tokenInfo, long expiration) {
        return Jwts.builder()
                .subject(tokenInfo.getUsername())
                .claim("name", tokenInfo.getName())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .issuedAt(new Date())
                .signWith(privateKey)
                .compact();
    }

    @Override
    public String getUsername(String token) {
        return extractAllClaims(token).get("sub", String.class);
    }


    @Override
    public boolean validateToken(String token) {
        return false;
    }

    @Override
    public boolean verifyUsername(String token, String username) {
        return false;
    }

    @Override
    public long getExpiration(String token) {
        return 0;
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String getUsername(Claims claims) {
        return "";
    }

    @Override
    public String getRole(Claims claims) {
        return "";
    }

    @Override
    public long getExpiration(Claims claims) {
        return 0;
    }

    @Override
    public TokenInfo getTokenInfo(String token) {
        return null;
    }
}
