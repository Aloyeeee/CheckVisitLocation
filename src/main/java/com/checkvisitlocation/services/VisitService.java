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

/**
 * Сервіс для роботи з відвідуваннями.
 * Надає методи для створення та пошуку відвідувань користувачів.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Service
public class VisitService {
    private static final Logger logger = LoggerFactory.getLogger(VisitService.class);
    private final VisitRepository visitRepository;
    private final LocationRepository locationRepository;

    /**
     * Конструктор для ініціалізації сервісу.
     * 
     * @param visitRepository репозиторій для роботи з відвідуваннями
     * @param locationRepository репозиторій для роботи з локаціями
     */
    public VisitService(VisitRepository visitRepository, LocationRepository locationRepository) {
        this.visitRepository = visitRepository;
        this.locationRepository = locationRepository;
    }

    /**
     * Отримує всі відвідування користувача.
     * 
     * @param user користувач
     * @return список відвідувань користувача
     */
    public List<VisitResponse> getUserVisits(User user) {
        List<Visit> visits = visitRepository.findByUser(user);
        return visits.stream()
                .map(VisitResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Отримує відвідування користувача за типами локацій.
     * 
     * @param user користувач
     * @param locationTypes список типів локацій
     * @return список відвідувань користувача вказаних типів локацій
     */
    public List<VisitResponse> getUserVisitsByLocationType(User user, List<LocationType> locationTypes) {
        List<Visit> visits = visitRepository.findByUserAndLocationTypeIn(user, locationTypes);
        return visits.stream()
                .map(VisitResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Отримує відвідування користувача за рейтингом.
     * 
     * @param user користувач
     * @param rating рейтинг (від 1 до 5)
     * @return список відвідувань користувача з вказаним рейтингом
     * @throws IllegalArgumentException якщо рейтинг не входить в діапазон від 1 до 5
     */
    public List<VisitResponse> getUserVisitsByRating(User user, Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        List<Visit> visits = visitRepository.findByUserAndRating(user, rating);
        return visits.stream()
                .map(VisitResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Створює нове відвідування.
     * Метод виконується в транзакції.
     * 
     * @param visitRequest дані для створення відвідування
     * @param user користувач, який здійснив відвідування
     * @return ідентифікатор створеного відвідування
     * @throws IllegalArgumentException якщо локація не знайдена
     */
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