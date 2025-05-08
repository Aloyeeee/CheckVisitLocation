package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.enums.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByTypeIn(List<String> types);

    @Query("SELECT l FROM Location l JOIN l.tags t WHERE t.type IN :tagTypes")
    List<Location> findByTagTypes(List<TagType> tagTypes);
}