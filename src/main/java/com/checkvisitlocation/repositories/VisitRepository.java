package com.checkvisitlocation.repositories;

import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторій для роботи з відвідуваннями (Visit).
 * Містить методи для пошуку, фільтрації та аналітики відвідувань.
 */
public interface VisitRepository extends JpaRepository<Visit, Long> {
    /**
     * Повертає всі відвідування користувача.
     * @param user користувач
     * @return список відвідувань
     */
    List<Visit> findByUser(User user);

    /**
     * Повертає всі відвідування у заданому діапазоні дат.
     * @param start початкова дата
     * @param end кінцева дата
     * @return список відвідувань
     */
    List<Visit> findByVisitDateBetween(LocalDate start, LocalDate end);

    /**
     * Повертає всі відвідування користувача у заданому діапазоні дат.
     * @param user користувач
     * @param start початкова дата
     * @param end кінцева дата
     * @return список відвідувань
     */
    List<Visit> findByUserAndVisitDateBetween(User user, LocalDate start, LocalDate end);

    /**
     * Повертає всі відвідування користувача за типами локацій.
     * @param user користувач
     * @param types список типів локацій
     * @return список відвідувань
     */
    List<Visit> findByUserAndLocationTypeIn(User user, List<LocationType> types);

    /**
     * Повертає всі відвідування користувача з певним рейтингом.
     * @param user користувач
     * @param rating рейтинг
     * @return список відвідувань
     */
    List<Visit> findByUserAndRating(User user, Integer rating);

    /**
     * Повертає середній рейтинг відвідувань користувача.
     * @param user користувач
     * @return середній рейтинг
     */
    @Query("SELECT AVG(v.rating) FROM Visit v WHERE v.user = :user")
    Double findAverageRatingByUser(User user);

    /**
     * Повертає середній рейтинг відвідувань користувача у заданому діапазоні дат.
     * @param user користувач
     * @param start початкова дата
     * @param end кінцева дата
     * @return середній рейтинг
     */
    @Query("SELECT AVG(v.rating) FROM Visit v WHERE v.user = :user AND v.visitDate BETWEEN :start AND :end")
    Double findAverageRatingByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}