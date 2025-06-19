package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Репозиторій для роботи з локаціями.
 * Надає методи для пошуку та управління локаціями в базі даних,
 * включаючи розширені запити з підрахунком відвідувань.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
    /**
     * Знаходить локації за списком типів.
     * 
     * @param types список типів локацій
     * @return список локацій, що відповідають вказаним типам
     */
    List<Location> findByTypeIn(List<LocationType> types);

    /**
     * Знаходить локації за списком типів тегів.
     * Використовує JPQL запит для пошуку локацій, які мають вказані теги.
     * 
     * @param tagTypes список типів тегів
     * @return список локацій, що мають вказані теги
     */
    @Query("SELECT l FROM Location l JOIN l.tags t WHERE t.type IN :tagTypes")
    List<Location> findByTagTypes(@Param("tagTypes") List<TagType> tagTypes);

    /**
     * Знаходить локації за списком типів тегів з підрахунком відвідувань.
     * Використовує JPQL запит для пошуку локацій та підрахунку їх відвідувань.
     * 
     * @param tagTypes список типів тегів
     * @return список масивів об'єктів, де перший елемент - локація, другий - кількість відвідувань
     */
    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v WHERE l IN (" +
            "SELECT l2 FROM Location l2 JOIN l2.tags t WHERE t.type IN :tagTypes) " +
            "GROUP BY l")
    List<Object[]> findByTagTypesWithVisitCount(@Param("tagTypes") List<TagType> tagTypes);

    /**
     * Знаходить локації за списком типів з підрахунком відвідувань.
     * Використовує JPQL запит для пошуку локацій та підрахунку їх відвідувань.
     * 
     * @param types список типів локацій
     * @return список масивів об'єктів, де перший елемент - локація, другий - кількість відвідувань
     */
    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v WHERE l.type IN :types " +
            "GROUP BY l")
    List<Object[]> findByTypesWithVisitCount(@Param("types") List<LocationType> types);

    /**
     * Знаходить всі локації з підрахунком відвідувань.
     * Використовує JPQL запит для пошуку всіх локацій та підрахунку їх відвідувань.
     * 
     * @return список масивів об'єктів, де перший елемент - локація, другий - кількість відвідувань
     */
    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v GROUP BY l")
    List<Object[]> findAllWithVisitCount();
}