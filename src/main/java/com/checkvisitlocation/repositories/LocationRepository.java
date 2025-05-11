package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByTypeIn(List<LocationType> types);

    @Query("SELECT l FROM Location l JOIN l.tags t WHERE t.type IN :tagTypes")
    List<Location> findByTagTypes(@Param("tagTypes") List<TagType> tagTypes);

    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v WHERE l IN (" +
            "SELECT l2 FROM Location l2 JOIN l2.tags t WHERE t.type IN :tagTypes) " +
            "GROUP BY l")
    List<Object[]> findByTagTypesWithVisitCount(@Param("tagTypes") List<TagType> tagTypes);

    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v WHERE l.type IN :types " +
            "GROUP BY l")
    List<Object[]> findByTypesWithVisitCount(@Param("types") List<LocationType> types);

    @Query("SELECT l, COUNT(v) FROM Location l LEFT JOIN l.visits v GROUP BY l")
    List<Object[]> findAllWithVisitCount();
}