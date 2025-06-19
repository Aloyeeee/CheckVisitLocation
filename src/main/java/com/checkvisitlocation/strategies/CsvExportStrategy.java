package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

/**
 * Стратегія експорту відвідувань у формат CSV.
 * Реалізує інтерфейс {@link ExportStrategy}.
 */
public class CsvExportStrategy implements ExportStrategy {
    /**
     * Експортує список відвідувань у формат CSV.
     * @param visits список відвідувань
     * @return CSV-рядок з відвідуваннями
     */
    @Override
    public String export(List<Visit> visits) {
        StringBuilder sb = new StringBuilder();
        sb.append("Location,Visit Date,Rating,Impressions\n");

        for (Visit visit : visits) {
            sb.append(escapeCsv(visit.getLocation().getName())).append(",")
                    .append(visit.getVisitDate()).append(",")
                    .append(visit.getRating()).append(",")
                    .append(escapeCsv(visit.getImpressions())).append("\n");
        }

        return sb.toString();
    }

    /**
     * Повертає розширення файлу для CSV експорту.
     * @return "csv"
     */
    @Override
    public String getFileExtension() {
        return "csv";
    }

    /**
     * Екранує значення для CSV (додає лапки, якщо потрібно).
     * @param input значення для екранування
     * @return екрановане значення
     */
    private String escapeCsv(String input) {
        if (input == null) return "";
        return "\"" + input.replace("\"", "\"\"") + "\"";
    }
}