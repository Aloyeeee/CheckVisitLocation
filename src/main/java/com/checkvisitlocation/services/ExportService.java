package com.checkvisitlocation.services;


import com.checkvisitlocation.enums.ExportFormat;
import com.checkvisitlocation.dtos.VisitResponse;
import com.checkvisitlocation.models.User;
import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.repositories.VisitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервіс для експорту відвідувань.
 * Надає методи для експорту відвідувань у різних форматах (CSV, JSON, TXT).
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
@Service
public class ExportService {
    private static final Logger logger = LoggerFactory.getLogger(ExportService.class);
    private final VisitRepository visitRepository;
    private final ObjectMapper objectMapper;

    /**
     * Конструктор для ініціалізації сервісу.
     * 
     * @param visitRepository репозиторій для роботи з відвідуваннями
     * @param objectMapper об'єкт для серіалізації JSON
     */
    public ExportService(VisitRepository visitRepository, ObjectMapper objectMapper) {
        this.visitRepository = visitRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Експортує відвідування користувача у вказаному форматі.
     * 
     * @param user користувач
     * @param format формат експорту
     * @return результат експорту, що містить ім'я файлу та його вміст
     * @throws IllegalStateException якщо виникла помилка при експорті
     */
    public ExportResult exportVisits(User user, ExportFormat format) {
        List<Visit> visits = visitRepository.findByUser(user);
        String filename = "visits_" + user.getUsername() + "." + format.getExtension();
        String content;

        try {
            content = switch (format) {
                case CSV -> toCsv(visits);
                case JSON -> toJson(visits);
                case TXT -> toText(visits);
            };
        } catch (Exception e) {
            logger.error("Failed to export visits for user {} in format {}: {}", user.getUsername(), format, e.getMessage());
            throw new IllegalStateException("Failed to export visits: " + e.getMessage());
        }

        return new ExportResult(filename, content);
    }

    /**
     * Конвертує список відвідувань у формат CSV.
     * 
     * @param visits список відвідувань
     * @return рядок у форматі CSV
     */
    private String toCsv(List<Visit> visits) {
        StringBuilder csv = new StringBuilder("id,location_name,visit_date,impressions,rating\n");
        for (Visit visit : visits) {
            if (visit.getLocation() == null) {
                logger.warn("Visit {} has null location", visit.getId());
                continue;
            }
            csv.append(String.format("%d,%s,%s,%s,%d\n",
                    visit.getId(),
                    escapeCsv(visit.getLocation().getName()),
                    visit.getVisitDate(),
                    escapeCsv(visit.getImpressions() != null ? visit.getImpressions() : ""),
                    visit.getRating()));
        }
        return csv.toString();
    }

    /**
     * Конвертує список відвідувань у формат JSON.
     * 
     * @param visits список відвідувань
     * @return рядок у форматі JSON
     * @throws Exception якщо виникла помилка при серіалізації
     */
    private String toJson(List<Visit> visits) throws Exception {
        return objectMapper.writeValueAsString(
                visits.stream()
                .map(VisitResponse::new)
                .collect(Collectors.toList()));
    }

    /**
     * Конвертує список відвідувань у текстовий формат.
     * 
     * @param visits список відвідувань
     * @return текстовий опис відвідувань
     */
    private String toText(List<Visit> visits) {
        return visits.stream()
                .filter(visit -> visit.getLocation() != null)
                .map(visit -> String.format("Visit #%d: %s on %s, Rating: %d, Impressions: %s",
                        visit.getId(),
                        visit.getLocation().getName(),
                        visit.getVisitDate(),
                        visit.getRating(),
                        visit.getImpressions() != null ? visit.getImpressions() : ""))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Екранує спеціальні символи для CSV формату.
     * 
     * @param value значення для екранування
     * @return екрановане значення
     */
    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    /**
     * Запис для зберігання результату експорту.
     * 
     * @param filename ім'я файлу
     * @param content вміст файлу
     */
    public record ExportResult(String filename, String content) {
    }
}
