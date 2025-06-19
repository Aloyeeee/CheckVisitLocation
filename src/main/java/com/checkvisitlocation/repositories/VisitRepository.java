package com.checkvisitlocation.repositories;

import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторій для роботи з відвідуваннями.
 * Надає методи для пошуку та аналізу відвідувань в базі даних,
 * включаючи розширені запити для статистики.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public interface VisitRepository extends JpaRepository<Visit, Long> {
    /**
     * Знаходить всі відвідування користувача.
     * 
     * @param user користувач
     * @return список відвідувань користувача
     */
    List<Visit> findByUser(User user);

    /**
     * Знаходить відвідування за діапазоном дат.
     * 
     * @param start початкова дата
     * @param end кінцева дата
     * @return список відвідувань у вказаному діапазоні дат
     */
    List<Visit> findByVisitDateBetween(LocalDate start, LocalDate end);

    /**
     * Знаходить відвідування користувача за діапазоном дат.
     * 
     * @param user користувач
     * @param start початкова дата
     * @param end кінцева дата
     * @return список відвідувань користувача у вказаному діапазоні дат
     */
    List<Visit> findByUserAndVisitDateBetween(User user, LocalDate start, LocalDate end);

    /**
     * Знаходить відвідування користувача за типами локацій.
     * 
     * @param user користувач
     * @param types список типів локацій
     * @return список відвідувань користувача вказаних типів локацій
     */
    List<Visit> findByUserAndLocationTypeIn(User user, List<LocationType> types);

    /**
     * Знаходить відвідування користувача за рейтингом.
     * 
     * @param user користувач
     * @param rating рейтинг
     * @return список відвідувань користувача з вказаним рейтингом
     */
    List<Visit> findByUserAndRating(User user, Integer rating);

    /**
     * Обчислює середній рейтинг відвідувань користувача.
     * Використовує JPQL запит для підрахунку середнього значення.
     * 
     * @param user користувач
     * @return середній рейтинг відвідувань користувача
     */
    @Query("SELECT AVG(v.rating) FROM Visit v WHERE v.user = :user")
    Double findAverageRatingByUser(User user);

    /**
     * Обчислює середній рейтинг відвідувань користувача за діапазоном дат.
     * Використовує JPQL запит для підрахунку середнього значення.
     * 
     * @param user користувач
     * @param start початкова дата
     * @param end кінцева дата
     * @return середній рейтинг відвідувань користувача у вказаному діапазоні дат
     */
    @Query("SELECT AVG(v.rating) FROM Visit v WHERE v.user = :user AND v.visitDate BETWEEN :start AND :end")
    Double findAverageRatingByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}