package com.checkvisitlocation.services;

import com.checkvisitlocation.dtos.VisitRequest;
import com.checkvisitlocation.dtos.VisitResponse;
import com.checkvisitlocation.models.Location;
import com.checkvisitlocation.enums.LocationType;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.repositories.LocationRepository;
import com.checkvisitlocation.repositories.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {
    private static final Logger logger = LoggerFactory.getLogger(VisitService.class);
    private final VisitRepository visitRepository;
    private final LocationRepository locationRepository;

    public VisitService(VisitRepository visitRepository, LocationRepository locationRepository) {
        this.visitRepository = visitRepository;
        this.locationRepository = locationRepository;
    }

    public List<VisitResponse> getUserVisits(User user) {
        List<Visit> visits = visitRepository.findByUser(user);
        return visits.stream()
                .map(VisitResponse::new)
                .collect(Collectors.toList());
    }

    public List<VisitResponse> getUserVisitsByLocationType(User user, List<LocationType> locationTypes) {
        List<Visit> visits = visitRepository.findByUserAndLocationTypeIn(user, locationTypes);
        return visits.stream()
                .map(VisitResponse::new)
                .collect(Collectors.toList());
    }

    public List<VisitResponse> getUserVisitsByRating(User user, Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        List<Visit> visits = visitRepository.findByUserAndRating(user, rating);
        return visits.stream()
                .map(VisitResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createVisit(VisitRequest visitRequest, User user) {

        Location location = locationRepository.findById(visitRequest.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("Location not found with ID: " + visitRequest.getLocationId()));

        Visit visit = visitRequest.toVisit();
        visit.setUser(user);
        visit.setLocation(location);
        logger.info("Visit saved for user {} at location {}", user.getUsername(), location.getName());

        Visit savedVisit = visitRepository.save(visit);
        return savedVisit.getId();
    }
}