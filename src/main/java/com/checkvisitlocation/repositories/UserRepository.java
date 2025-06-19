package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Репозиторій для роботи з користувачами.
 * Надає методи для пошуку та управління користувачами в базі даних.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Знаходить користувача за його ім'ям.
     * 
     * @param username ім'я користувача
     * @return Optional, що містить користувача, якщо він знайдений
     */
    Optional<User> findByUsername(String username);

    /**
     * Перевіряє, чи існує користувач з вказаним ім'ям.
     * 
     * @param username ім'я користувача
     * @return true, якщо користувач існує, false в іншому випадку
     */
    boolean existsByUsername(String username);
}