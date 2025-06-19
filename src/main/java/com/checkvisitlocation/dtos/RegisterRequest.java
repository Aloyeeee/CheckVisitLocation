package com.checkvisitlocation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * DTO для запиту на реєстрацію користувача.
 */
public class RegisterRequest {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    /** Ім'я користувача. */
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    /** Пароль користувача. */
    private String password;

    private List<String> roles;

    // Геттери та сеттери
    /** Повертає ім'я користувача. */
    public String getUsername() {
        return username;
    }

    /** Встановлює ім'я користувача. */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Повертає пароль користувача. */
    public String getPassword() {
        return password;
    }

    /** Встановлює пароль користувача. */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Повертає список ролей користувача. */
    public List<String> getRoles() {
        return roles;
    }

    /** Встановлює список ролей користувача. */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}