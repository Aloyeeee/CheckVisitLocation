package com.checkvisitlocation.services;

import com.checkvisitlocation.dtos.RegisterRequest;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.repositories.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервіс для роботи з користувачами та аутентифікації.
 * Реалізує {@link UserDetailsService} для інтеграції зі Spring Security.
 */
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор сервісу UserService.
     * @param userRepository репозиторій користувачів
     * @param passwordEncoder енкодер паролів
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Завантажує користувача за ім'ям користувача для Spring Security.
     * @param username ім'я користувача
     * @return деталі користувача
     * @throws UsernameNotFoundException якщо користувача не знайдено
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Transactional
    /**
     * Реєструє нового користувача.
     * @param request запит на реєстрацію
     * @return створений користувач
     * @throws IllegalArgumentException якщо ім'я користувача вже існує
     */
    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    /**
     * Аутентифікує користувача за ім'ям та паролем.
     * @param username ім'я користувача
     * @param password пароль
     * @return деталі користувача
     * @throws IllegalArgumentException якщо ім'я або пароль null
     * @throws BadCredentialsException якщо облікові дані невалідні
     */
    public UserDetails authenticate(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }

        UserDetails userDetails = loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return userDetails;
    }
}