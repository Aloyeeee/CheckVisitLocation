package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Відвідувач для експорту даних у форматі JSON.
 * Реалізує інтерфейс {@link DataExportVisitor} для Visit та AnalyticsResponse.
 */
public class JsonExportVisitor implements DataExportVisitor {
    private final ObjectMapper objectMapper;

    /**
     * Конструктор з налаштуванням ObjectMapper для створення читабельного JSON.
     */
    public JsonExportVisitor() {
        this.objectMapper = new ObjectMapper();
        
        // Налаштування для обробки дат
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        // Виключення null значень
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        // Красиве форматування JSON
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Експортує об'єкт Visit у форматі JSON.
     * @param visit відвідування для експорту
     * @return JSON-рядок з даними відвідування
     * @throws RuntimeException якщо експорт у JSON не вдався
     */
    @Override
    public String visit(Visit visit) {
        try {
            if (visit == null) {
                return "null";
            }
            
            Map<String, Object> visitData = new HashMap<>();
            visitData.put("id", visit.getId());
            visitData.put("visitDate", visit.getVisitDate());
            visitData.put("rating", visit.getRating());
            visitData.put("impressions", visit.getImpressions());
            
            // Інформація про користувача (без циклічних посилань)
            if (visit.getUser() != null) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("id", visit.getUser().getId());
                userData.put("username", visit.getUser().getUsername());
                visitData.put("user", userData);
            }
            
            // Інформація про локацію (без циклічних посилань)
            if (visit.getLocation() != null) {
                Map<String, Object> locationData = new HashMap<>();
                locationData.put("id", visit.getLocation().getId());
                locationData.put("name", visit.getLocation().getName());
                locationData.put("type", visit.getLocation().getType());
                locationData.put("address", visit.getLocation().getAddress());
                locationData.put("description", visit.getLocation().getDescription());
                visitData.put("location", locationData);
            }
            
            return objectMapper.writeValueAsString(visitData);
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed for Visit: " + e.getMessage(), e);
        }
    }

    /**
     * Експортує об'єкт AnalyticsResponse у форматі JSON.
     * @param analytics аналітична відповідь для експорту
     * @return JSON-рядок з аналітичними даними
     * @throws RuntimeException якщо експорт у JSON не вдався
     */
    @Override
    public String visit(AnalyticsResponse analytics) {
        try {
            if (analytics == null) {
                return "null";
            }
            
            Map<String, Object> analyticsData = new HashMap<>();
            analyticsData.put("averageRating", analytics.getAverageRating());
            analyticsData.put("totalVisits", analytics.getTotalVisits());
            analyticsData.put("visitsByType", analytics.getVisitsByType());
            analyticsData.put("report", analytics.getReport());
            
            // Кількість відфільтрованих відвідувань
            if (analytics.getFilteredVisits() != null) {
                analyticsData.put("filteredVisitsCount", analytics.getFilteredVisits().size());
            }
            
            return objectMapper.writeValueAsString(analyticsData);
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed for AnalyticsResponse: " + e.getMessage(), e);
        }
    }
}