package com.checkvisitlocation.services;

import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.repositories.LocationRepository;
import com.checkvisitlocation.repositories.VisitRepository;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VisitService {
    private static final Logger logger = LoggerFactory.getLogger(VisitService.class);
    private final VisitRepository visitRepository;
    private final LocationRepository locationRepository;
    private final Validator validator;

    public VisitService(VisitRepository visitRepository, LocationRepository locationRepository, Validator validator) {
        this.visitRepository = visitRepository;
        this.locationRepository = locationRepository;
        this.validator = validator;
    }

    public List<Visit> getUserVisits(User user) {
        return visitRepository.findByUser(user);
    }

    @Transactional
    public Visit addVisit(User user, Location location, Visit visit) {
        // Валідація об'єкта Visit
        Set<jakarta.validation.ConstraintViolation<Visit>> violations = validator.validate(visit);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining(", "));
            logger.error("Validation failed for visit: {}", errors);
            throw new IllegalArgumentException("Validation failed: " + errors);
        }

        // Перевірка існування локації
        Location existingLocation = locationRepository.findById(location.getId())
                .orElseThrow(() -> {
                    logger.error("Location not found with ID: {}", location.getId());
                    return new IllegalArgumentException("Location not found with ID: " + location.getId());
                });

        // Встановлення user і location
        visit.setUser(user);
        visit.setLocation(existingLocation);

        // Збереження відвідування
        Visit savedVisit = visitRepository.save(visit);
        logger.info("Visit saved for user {} at location {}", user.getUsername(), existingLocation.getName());
        return savedVisit;
    }
}