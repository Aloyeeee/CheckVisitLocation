package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

/**
 * Стратегія експорту відвідувань у текстовий формат.
 * Реалізує інтерфейс {@link ExportStrategy}.
 */
public class TxtExportStrategy implements ExportStrategy {
    /**
     * Експортує список відвідувань у текстовий формат.
     * @param visits список відвідувань
     * @return текстовий звіт
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
     * Повертає розширення файлу для текстового експорту.
     * @return "txt"
     */
    @Override
    public String getFileExtension() {
        return "txt";
    }
}