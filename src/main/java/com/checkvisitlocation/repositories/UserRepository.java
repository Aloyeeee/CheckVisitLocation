package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Репозиторій для роботи з користувачами (User).
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Повертає користувача за ім'ям користувача.
     * @param username ім'я користувача
     * @return Optional з користувачем, якщо знайдено
     */
    Optional<User> findByUsername(String username);
    /**
     * Перевіряє, чи існує користувач з таким ім'ям.
     * @param username ім'я користувача
     * @return true, якщо користувач існує
     */
    boolean existsByUsername(String username);
}