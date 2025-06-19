package com.checkvisitlocation.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * Клас, що представляє запит на вхід в систему.
 * Містить дані для автентифікації користувача.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class LoginRequest {
    /**
     * Логін користувача.
     * Не може бути порожнім.
     */
    @NotBlank(message = "Username cannot be blank")
    private String username;

    /**
     * Пароль користувача.
     * Не може бути порожнім.
     */
    @NotBlank(message = "Password cannot be blank")
    private String password;

    /**
     * Отримує логін користувача.
     * 
     * @return логін користувача
     */
    public String getUsername() { return username; }

    /**
     * Встановлює логін користувача.
     * 
     * @param username логін користувача
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Отримує пароль користувача.
     * 
     * @return пароль користувача
     */
    public String getPassword() { return password; }

    /**
     * Встановлює пароль користувача.
     * 
     * @param password пароль користувача
     */
    public void setPassword(String password) { this.password = password; }
}