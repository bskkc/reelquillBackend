package com.example.reelquill.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

    private final String TOKEN_PREFIX = "Bearer ";

    private String generateRandomSecretKey() {
        byte[] randomBytes = new byte[64];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

    public String createToken(String email) {
        String secretKey = generateRandomSecretKey();

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return TOKEN_PREFIX + token;
    }

    public String extractEmail(String token, String secretKey) {
        return extractAllClaims(token, secretKey).getSubject();
    }

    private Claims extractAllClaims(String token, String secretKey) {
        if (token.startsWith(TOKEN_PREFIX)) {
            token = token.substring(TOKEN_PREFIX.length());
        }
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token, String secretKey) {
        return extractAllClaims(token, secretKey).getExpiration().before(new Date());
    }
}
