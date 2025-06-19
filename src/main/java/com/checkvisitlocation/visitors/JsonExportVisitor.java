package com.checkvisitlocation.visitors;

import com.checkvisitlocation.models.Visit;
import com.checkvisitlocation.dtos.AnalyticsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Відвідувач для експорту даних у форматі JSON.
 * Реалізує інтерфейс {@link DataExportVisitor} для Visit та AnalyticsResponse.
 */
public class JsonExportVisitor implements DataExportVisitor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Експортує об'єкт Visit у форматі JSON.
     * @param visit відвідування для експорту
     * @return JSON-рядок з даними відвідування
     * @throws RuntimeException якщо експорт у JSON не вдався
     */
    @Override
    public String visit(Visit visit) {
        try {
            return objectMapper.writeValueAsString(visit);
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed", e);
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
            return objectMapper.writeValueAsString(analytics);
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed", e);
        }
    }
}