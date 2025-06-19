package com.checkvisitlocation.models;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Сутність "Користувач" (User).
 * Реалізує {@link UserDetails} для інтеграції зі Spring Security.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** Ідентифікатор користувача. */
    private Long id;

    @Column(unique = true, nullable = false)
    /** Унікальне ім'я користувача. */
    private String username;

    @Column(nullable = false)
    /** Пароль користувача. */
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Повертає ідентифікатор користувача.
     */
    public Long getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор користувача.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Встановлює ім'я користувача.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Встановлює пароль користувача.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}