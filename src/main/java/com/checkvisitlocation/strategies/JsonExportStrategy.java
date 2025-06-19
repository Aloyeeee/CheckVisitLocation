package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * Реалізація стратегії експорту у форматі JSON.
 * Конвертує дані про відвідування у формат JSON з використанням бібліотеки Jackson.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class JsonExportStrategy implements ExportStrategy {
    /**
     * Об'єкт для серіалізації даних у JSON формат.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Експортує список відвідувань у форматі JSON.
     * Конвертує об'єкти Visit у JSON з гарним форматуванням.
     * 
     * @param visits список відвідувань для експорту
     * @return рядок у форматі JSON з даними про відвідування
     * @throws RuntimeException якщо виникла помилка при конвертації в JSON
     */
    @Override
    public String export(List<Visit> visits) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(visits);
        } catch (Exception e) {
            throw new RuntimeException("JSON export failed", e);
        }
    }

    /**
     * Отримує розширення файлу для JSON формату.
     * 
     * @return рядок "json"
     */
    @Override
    public String getFileExtension() {
        return "json";
    }
}