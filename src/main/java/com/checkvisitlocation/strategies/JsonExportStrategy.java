package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

/**
 * Стратегія експорту відвідувань у формат JSON.
 * Реалізує інтерфейс {@link ExportStrategy}.
 */
public class JsonExportStrategy implements ExportStrategy {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Експортує список відвідувань у формат JSON.
     * @param visits список відвідувань
     * @return JSON-рядок з відвідуваннями
     * @throws RuntimeException якщо експорт у JSON не вдався
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
     * Повертає розширення файлу для JSON експорту.
     * @return "json"
     */
    @Override
    public String getFileExtension() {
        return "json";
    }
}