package com.checkvisitlocation.strategies;

import com.checkvisitlocation.models.Visit;
import java.util.List;

/**
 * Реалізація стратегії експорту у форматі CSV.
 * Конвертує дані про відвідування у формат CSV з екрануванням спеціальних символів.
 * 
 * @author CheckVisitLocation Team
 * @version 1.0
 * @since 2025
 */
public class CsvExportStrategy implements ExportStrategy {
    /**
     * Експортує список відвідувань у форматі CSV.
     * Створює CSV файл з заголовками та даними про відвідування,
     * де кожен рядок містить назву локації, дату відвідування, рейтинг та враження.
     * 
     * @param visits список відвідувань для експорту
     * @return рядок у форматі CSV з даними про відвідування
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
     * Отримує розширення файлу для CSV формату.
     * 
     * @return рядок "csv"
     */
    @Override
    public String getFileExtension() {
        return "csv";
    }

    /**
     * Екранує спеціальні символи в CSV форматі.
     * Огортає значення в лапки та подвоює лапки всередині значення.
     * 
     * @param input рядок для екранування
     * @return екранований рядок, безпечний для використання в CSV
     */
    private String escapeCsv(String input) {
        if (input == null) return "";
        return "\"" + input.replace("\"", "\"\"") + "\"";
    }
}