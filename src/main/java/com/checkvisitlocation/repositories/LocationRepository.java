package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByNameContainingIgnoreCase(String name);
    List<Location> findByDescriptionContainingIgnoreCase(String description);
}