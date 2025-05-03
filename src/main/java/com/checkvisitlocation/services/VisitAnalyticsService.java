package com.checkvisitlocation.services;

import com.checkvisitlocation.dtos.AnalyticsRequest;
import com.checkvisitlocation.dtos.AnalyticsResponse;
import com.checkvisitlocation.models.LocationType;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.repositories.VisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VisitAnalyticsService {
    private static final Logger logger = LoggerFactory.getLogger(VisitAnalyticsService.class);
    private final VisitRepository visitRepository;
    private final ObjectMapper objectMapper;

    public VisitAnalyticsService(VisitRepository visitRepository, ObjectMapper objectMapper) {
        this.visitRepository = visitRepository;
        this.objectMapper = objectMapper;
    }

    public AnalyticsResponse analyzeVisits(User user, AnalyticsRequest request) {
        logger.info("Analyzing visits for user {}", user.getUsername());

        // Отримання відвідувань
        List<Visit> visits = getFilteredVisits(user, request);

        // Обчислення середнього рейтингу
        Double averageRating = calculateAverageRating(user, request);

        // Підрахунок відвідувань за типами
        Map<String, Long> visitsByType = visits.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getLocation().getType().name(),
                        Collectors.counting()
                ));

        // Сортування відвідувань
        visits = sortVisits(visits, request.getSortBy(), request.getSortOrder());

        // Генерація звіту
        String report = generateReport(averageRating, visits, visitsByType, request.getReportFormat());

        // Формування відповіді
        AnalyticsResponse response = new AnalyticsResponse();
        response.setAverageRating(averageRating);
        response.setTotalVisits((long) visits.size());
        response.setVisitsByType(visitsByType);
        response.setFilteredVisits(visits);
        response.setReport(report);

        return response;
    }

    private List<Visit> getFilteredVisits(User user, AnalyticsRequest request) {
        List<Visit> visits;

        // Фільтрація за періодом
        if (request.getStartDate() != null && request.getEndDate() != null) {
            visits = visitRepository.findByUserAndVisitDateBetween(user, request.getStartDate(), request.getEndDate());
        } else {
            visits = visitRepository.findByUser(user);
        }

        // Фільтрація за типами локацій
        if (request.getLocationTypes() != null && !request.getLocationTypes().isEmpty()) {
            visits = visits.stream()
                    .filter(v -> request.getLocationTypes().contains(v.getLocation().getType()))
                    .collect(Collectors.toList());
        }

        // Фільтрація за відстанню
        if (request.getLatitude() != null && request.getLongitude() != null && request.getMaxDistance() != null) {
            visits = visits.stream()
                    .filter(v -> isWithinDistance(v.getLocation().getGeoTag(), request.getLatitude(), request.getLongitude(), request.getMaxDistance()))
                    .collect(Collectors.toList());
        }

        return visits;
    }

    private Double calculateAverageRating(User user, AnalyticsRequest request) {
        if (request.getStartDate() != null && request.getEndDate() != null) {
            return visitRepository.findAverageRatingByUserAndDateBetween(user, request.getStartDate(), request.getEndDate());
        }
        return visitRepository.findAverageRatingByUser(user);
    }

    private List<Visit> sortVisits(List<Visit> visits, String sortBy, String sortOrder) {
        Comparator<Visit> comparator;
        switch (sortBy == null ? "date" : sortBy.toLowerCase()) {
            case "rating":
                comparator = Comparator.comparing(Visit::getRating);
                break;
            case "locationName":
                comparator = Comparator.comparing(v -> v.getLocation().getName());
                break;
            case "date":
            default:
                comparator = Comparator.comparing(Visit::getVisitDate);
                break;
        }

        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        return visits.stream().sorted(comparator).collect(Collectors.toList());
    }

    private boolean isWithinDistance(String geoTag, double latitude, double longitude, double maxDistance) {
        if (geoTag == null || !geoTag.matches("^-?\\d{1,3}\\.\\d+,-?\\d{1,3}\\.\\d+$")) {
            return false;
        }

        String[] coords = geoTag.split(",");
        double locLatitude = Double.parseDouble(coords[0]);
        double locLongitude = Double.parseDouble(coords[1]);

        double distance = calculateHaversineDistance(latitude, longitude, locLatitude, locLongitude);
        return distance <= maxDistance;
    }

    private double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371; // Радіус Землі в кілометрах
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private String generateReport(Double averageRating, List<Visit> visits, Map<String, Long> visitsByType, String format) {
        try {
            if ("json".equalsIgnoreCase(format)) {
                Map<String, Object> reportData = new HashMap<>();
                reportData.put("averageRating", averageRating != null ? averageRating : 0.0);
                reportData.put("totalVisits", visits.size());
                reportData.put("visitsByType", visitsByType);
                return objectMapper.writeValueAsString(reportData);
            } else {
                StringBuilder report = new StringBuilder();
                report.append("Visit Analytics Report\n");
                report.append("=====================\n");
                report.append(String.format("Average Rating: %.2f\n", averageRating != null ? averageRating : 0.0));
                report.append("Total Visits: ").append(visits.size()).append("\n");
                report.append("Visits by Location Type:\n");
                visitsByType.forEach((type, count) ->
                        report.append(String.format("  %s: %d\n", type, count)));
                return report.toString();
            }
        } catch (Exception e) {
            logger.error("Failed to generate report: {}", e.getMessage());
            return "Error generating report: " + e.getMessage();
        }
    }
}