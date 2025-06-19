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
 * Утиліта для роботи з JWT токенами.
 * Надає методи для генерації, валідації та отримання даних з JWT токенів.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Component
public class JwtTokenUtil {
    private final SecretKey secretKey;
    private final Long expiration;

    /**
     * Створює новий екземпляр утиліти для роботи з JWT токенами.
     * 
     * @param secret секретний ключ для підпису токенів
     * @param expiration час життя токена в мілісекундах
     * @throws IllegalArgumentException якщо секретний ключ менше 32 символів
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
     * 
     * @param userDetails дані користувача
     * @return згенерований JWT токен
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
     * 
     * @param token JWT токен
     * @return ім'я користувача
     * @throws JwtException якщо токен невалідний або прострочений
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
     * Перевіряє валідність JWT токена для користувача.
     * 
     * @param token JWT токен
     * @param userDetails дані користувача
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

    /**
     * Перевіряє, чи прострочений JWT токен.
     * 
     * @param token JWT токен
     * @return true, якщо токен прострочений
     */
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