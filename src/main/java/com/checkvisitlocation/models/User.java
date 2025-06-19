package com.checkvisitlocation.models;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Сутність, що представляє користувача системи.
 * Реалізує інтерфейс UserDetails для інтеграції з Spring Security.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {
    /**
     * Унікальний ідентифікатор користувача.
     * Генерується автоматично.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Унікальне ім'я користувача.
     * Не може бути null.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Захешований пароль користувача.
     * Не може бути null.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Отримує список ролей користувача.
     * За замовчуванням всі користувачі мають роль "USER".
     * 
     * @return колекція ролей користувача
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    /**
     * Отримує пароль користувача.
     * 
     * @return захешований пароль
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Отримує ім'я користувача.
     * 
     * @return ім'я користувача
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Перевіряє, чи не закінчився термін дії облікового запису.
     * За замовчуванням повертає true.
     * 
     * @return true, якщо обліковий запис активний
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Перевіряє, чи не заблокований обліковий запис.
     * За замовчуванням повертає true.
     * 
     * @return true, якщо обліковий запис не заблокований
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Перевіряє, чи не закінчився термін дії облікових даних.
     * За замовчуванням повертає true.
     * 
     * @return true, якщо облікові дані активні
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Перевіряє, чи активний обліковий запис.
     * За замовчуванням повертає true.
     * 
     * @return true, якщо обліковий запис активний
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Отримує ідентифікатор користувача.
     * 
     * @return ідентифікатор користувача
     */
    public Long getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор користувача.
     * 
     * @param id ідентифікатор користувача
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Встановлює ім'я користувача.
     * 
     * @param username ім'я користувача
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Встановлює пароль користувача.
     * 
     * @param password захешований пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }
}