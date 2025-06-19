package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

/**
 * Реалізація стратегії експорту у текстовому форматі.
 * Конвертує дані про відвідування у зручний для читання текстовий формат.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class TxtExportStrategy implements ExportStrategy {
    /**
     * Експортує список відвідувань у текстовому форматі.
     * Створює звіт з заголовком та детальною інформацією про кожне відвідування,
     * включаючи назву локації, дату, рейтинг та враження.
     * 
     * @param visits список відвідувань для експорту
     * @return рядок у текстовому форматі з даними про відвідування
     */
    @Override
    public String export(List<Visit> visits) {
        StringBuilder sb = new StringBuilder();
        sb.append("Visited Locations Report\n\n");

        for (Visit visit : visits) {
            sb.append("Location: ").append(visit.getLocation().getName()).append("\n")
                    .append("Date: ").append(visit.getVisitDate()).append("\n")
                    .append("Rating: ").append(visit.getRating()).append("/5\n")
                    .append("Impressions: ").append(visit.getImpressions()).append("\n\n");
        }

        return sb.toString();
    }

    /**
     * Отримує розширення файлу для текстового формату.
     * 
     * @return рядок "txt"
     */
    @Override
    public String getFileExtension() {
        return "txt";
    }
}