package com.checkvisitlocation.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Клас-обгортка для відповіді аутентифікації, що містить JWT токен.
 */
public class AuthResponse {
    @JsonProperty("token")
    private String token;

    /**
     * Конструктор для створення відповіді з токеном.
     * @param token JWT токен
     */
    public AuthResponse(String token) {
        this.token = token;
    }

    /**
     * Повертає JWT токен.
     * @return JWT токен
     */
    public String getToken() {
        return token;
    }
}