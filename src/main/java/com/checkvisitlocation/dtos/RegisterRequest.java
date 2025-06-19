package com.checkvisitlocation.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Клас, що представляє запит на реєстрацію нового користувача.
 * Містить дані для створення нового облікового запису.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class RegisterRequest {
    /**
     * Логін користувача.
     * Не може бути порожнім та має бути від 3 до 50 символів.
     */
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    /**
     * Пароль користувача.
     * Не може бути порожнім та має бути від 6 до 100 символів.
     */
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    /**
     * Список ролей користувача.
     * Визначає права доступу користувача в системі.
     */
    private List<String> roles;

    /**
     * Отримує логін користувача.
     * 
     * @return логін користувача
     */
    public String getUsername() {
        return username;
    }

    /**
     * Встановлює логін користувача.
     * 
     * @param username логін користувача
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Отримує пароль користувача.
     * 
     * @return пароль користувача
     */
    public String getPassword() {
        return password;
    }

    /**
     * Встановлює пароль користувача.
     * 
     * @param password пароль користувача
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Отримує список ролей користувача.
     * 
     * @return список ролей
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Встановлює список ролей користувача.
     * 
     * @param roles список ролей
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}