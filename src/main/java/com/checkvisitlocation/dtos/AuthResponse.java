package com.checkvisitlocation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO для відповіді з JWT токеном після аутентифікації.
 */
public class AuthResponse {
    @JsonProperty("token")
    /** JWT токен. */
    private String token;

    /**
     * Конструктор з токеном.
     * @param token JWT токен
     */
    public AuthResponse(String token) {
        this.token = token;
    }

    /** Повертає JWT токен. */
    public String getToken() {
        return token;
    }
}