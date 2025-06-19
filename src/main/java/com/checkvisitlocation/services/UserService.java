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
 * Сервіс для роботи з користувачами.
 * Надає методи для реєстрації, аутентифікації та завантаження даних користувача.
 * Реалізує інтерфейс UserDetailsService для інтеграції з Spring Security.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для ініціалізації сервісу.
     * 
     * @param userRepository репозиторій для роботи з користувачами
     * @param passwordEncoder кодувальник паролів
     */
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Завантажує користувача за іменем користувача.
     * Реалізація методу з інтерфейсу UserDetailsService.
     * 
     * @param username ім'я користувача
     * @return об'єкт UserDetails з даними користувача
     * @throws UsernameNotFoundException якщо користувач не знайдений
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    /**
     * Реєструє нового користувача.
     * Метод виконується в транзакції.
     * 
     * @param request дані для реєстрації
     * @return створений користувач
     * @throws IllegalArgumentException якщо ім'я користувача вже існує
     */
    @Transactional
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
     * Аутентифікує користувача за іменем та паролем.
     * 
     * @param username ім'я користувача
     * @param password пароль
     * @return об'єкт UserDetails з даними користувача
     * @throws IllegalArgumentException якщо ім'я користувача або пароль є null
     * @throws BadCredentialsException якщо облікові дані невірні
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