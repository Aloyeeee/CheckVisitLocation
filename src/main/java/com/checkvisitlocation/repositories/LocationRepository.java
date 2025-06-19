package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Репозиторій для роботи з локаціями (Location).
 * Містить методи для фільтрації, підрахунку відвідувань та пошуку за тегами/типами.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {
    /**
     * Повертає локації за списком типів.
     * @param types список типів локацій
     * @return список локацій
     */
    List<Location> findByTypeIn(List<LocationType> types);

    /**
     * Повертає локації, які мають хоча б один із заданих типів тегів.
     * @param tagTypes список типів тегів
     * @return список локацій
     */
    @Query("SELECT l FROM Location l JOIN l.tags t WHERE t.type IN :tagTypes")
    List<Location> findByTagTypes(@Param("tagTypes") List<TagType> tagTypes);

    /**
     * Повертає локації з кількістю відвідувань, відфільтровані за тегами.
     * @param tagTypes список типів тегів
     * @return список масивів [локація, кількість відвідувань]
     */
    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v WHERE l IN (" +
            "SELECT l2 FROM Location l2 JOIN l2.tags t WHERE t.type IN :tagTypes) " +
            "GROUP BY l")
    List<Object[]> findByTagTypesWithVisitCount(@Param("tagTypes") List<TagType> tagTypes);

    /**
     * Повертає локації з кількістю відвідувань, відфільтровані за типами локацій.
     * @param types список типів локацій
     * @return список масивів [локація, кількість відвідувань]
     */
    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v WHERE l.type IN :types " +
            "GROUP BY l")
    List<Object[]> findByTypesWithVisitCount(@Param("types") List<LocationType> types);

    /**
     * Повертає всі локації з кількістю відвідувань.
     * @return список масивів [локація, кількість відвідувань]
     */
    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v GROUP BY l")
    List<Object[]> findAllWithVisitCount();
}