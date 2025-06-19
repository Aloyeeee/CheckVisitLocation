package com.checkvisitlocation.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Утилітний клас для роботи з JWT токенами: генерація, валідація, отримання імені користувача.
 */
@Component
public class JwtTokenUtil {
    private final SecretKey secretKey;
    private final Long expiration;

    /**
     * Конструктор JwtTokenUtil.
     * @param secret секретний ключ (мінімум 32 символи)
     * @param expiration час життя токена у мілісекундах
     * @throws IllegalArgumentException якщо секретний ключ занадто короткий
     */
    public JwtTokenUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") Long expiration) {
        if (secret == null || secret.length() < 32) {
            throw new IllegalArgumentException("JWT secret must be at least 32 characters long");
        }
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    /**
     * Генерує JWT токен для користувача.
     * @param userDetails деталі користувача
     * @return JWT токен
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Отримує ім'я користувача з JWT токена.
     * @param token JWT токен
     * @return ім'я користувача
     * @throws JwtException якщо токен недійсний або прострочений
     */
    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new JwtException("Token has expired", e);
        } catch (JwtException e) {
            throw new JwtException("Invalid token", e);
        }
    }

    /**
     * Перевіряє валідність токена для заданого користувача.
     * @param token JWT токен
     * @param userDetails деталі користувача
     * @return true, якщо токен валідний
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = getUsernameFromToken(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            Date expiration = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration();
            return expiration.before(new Date());
        } catch (JwtException e) {
            return true; // Вважаємо токен простроченим у разі помилки
        }
    }
}