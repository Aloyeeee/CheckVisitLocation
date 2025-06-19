package com.checkvisitlocation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Клас, що представляє відповідь на запит автентифікації.
 * Містить JWT токен для авторизації користувача.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class AuthResponse {
    /**
     * JWT токен для авторизації.
     * Серіалізується в JSON як "token".
     */
    @JsonProperty("token")
    private String token;

    /**
     * Створює новий об'єкт відповіді з JWT токеном.
     * 
     * @param token JWT токен для авторизації
     */
    public AuthResponse(String token) {
        this.token = token;
    }

    /**
     * Отримує JWT токен.
     * 
     * @return JWT токен для авторизації
     */
    public String getToken() {
        return token;
    }
}