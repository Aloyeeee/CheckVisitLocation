package com.checkvisitlocation.repositories;

import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByUser(User user);
    List<Visit> findByVisitDateBetween(LocalDate start, LocalDate end);
}