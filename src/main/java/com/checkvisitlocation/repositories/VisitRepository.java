package com.checkvisitlocation.repositories;

import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByUser(User user);
    List<Visit> findByVisitDateBetween(LocalDate start, LocalDate end);
    List<Visit> findByUserAndVisitDateBetween(User user, LocalDate start, LocalDate end);
    List<Visit> findByUserAndLocationTypeIn(User user, List<LocationType> types);
    List<Visit> findByUserAndRating(User user, Integer rating);

    @Query("SELECT AVG(v.rating) FROM Visit v WHERE v.user = :user")
    Double findAverageRatingByUser(User user);

    @Query("SELECT AVG(v.rating) FROM Visit v WHERE v.user = :user AND v.visitDate BETWEEN :start AND :end")
    Double findAverageRatingByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}