package com.checkvisitlocation.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO для запиту на вхід користувача.
 */
public class LoginRequest {
    @NotBlank(message = "Username cannot be blank")
    /** Ім'я користувача. */
    private String username;

    @NotBlank(message = "Password cannot be blank")
    /** Пароль користувача. */
    private String password;

    // Геттери та сеттери
    /** Повертає ім'я користувача. */
    public String getUsername() { return username; }
    /** Встановлює ім'я користувача. */
    public void setUsername(String username) { this.username = username; }
    /** Повертає пароль користувача. */
    public String getPassword() { return password; }
    /** Встановлює пароль користувача. */
    public void setPassword(String password) { this.password = password; }
}